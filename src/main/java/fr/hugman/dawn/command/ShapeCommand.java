package fr.hugman.dawn.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import fr.hugman.dawn.registry.DawnRegistryKeys;
import fr.hugman.dawn.shape.ConfiguredShape;
import net.minecraft.block.Block;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgument;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.command.argument.RegistryKeyArgumentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class ShapeCommand {
	public static final String NAME = "shape";
	public static final String CONFIGURED_SHAPE_ARG = "configured_shape";
	public static final String POS_ARG = "pos";
	public static final String FILL_ARG = "fill";
	public static final String BLOCK_ARG = "block";

	private static final DynamicCommandExceptionType INVALID_SHAPE_EXCEPTION = new DynamicCommandExceptionType((id) -> Text.translatable("commands.shape.shape.invalid", id));

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
		dispatcher.register(CommandManager.literal(NAME).requires(source -> source.hasPermissionLevel(2))
				.then(CommandManager.argument(CONFIGURED_SHAPE_ARG, RegistryKeyArgumentType.registryKey(DawnRegistryKeys.CONFIGURED_SHAPE))
						.then(CommandManager.argument(POS_ARG, BlockPosArgumentType.blockPos())
								.then(CommandManager.literal(FILL_ARG)
										.then(CommandManager.argument(BLOCK_ARG, BlockStateArgumentType.blockState(registryAccess))
												.executes(context -> fillShape(context.getSource(), getConfiguredShapeEntry(context, CONFIGURED_SHAPE_ARG), BlockPosArgumentType.getLoadedBlockPos(context, POS_ARG), BlockStateArgumentType.getBlockState(context, BLOCK_ARG))))))));
	}

	private static int fillShape(ServerCommandSource source, RegistryEntry.Reference<ConfiguredShape> configuredShapeEntry, BlockPos origin, BlockStateArgument stateArgument) throws CommandSyntaxException {
		ConfiguredShape configuredShape = configuredShapeEntry.value();
		ServerWorld world = source.getWorld();

		Shape shape = configuredShape.get(source.getWorld().getRandom());
		List<Position> posList = shape.stream().toList();

		shape = shape.applyLayer(new TranslateLayer(Position.of(origin)));

		for(var pos : shape.stream().toList()) {
			BlockPos blockPos = pos.toBlockPos();
			Clearable.clear(world.getBlockEntity(blockPos));
			stateArgument.setBlockState(world, blockPos, Block.NOTIFY_LISTENERS);
		}

		source.sendFeedback(Text.translatable("commands.fill.success", posList.size()), true);
		return posList.size();
	}

	public static RegistryEntry.Reference<ConfiguredShape> getConfiguredShapeEntry(CommandContext<ServerCommandSource> context, String name) throws CommandSyntaxException {
		return RegistryKeyArgumentType.getRegistryEntry(context, name, DawnRegistryKeys.CONFIGURED_SHAPE, INVALID_SHAPE_EXCEPTION);
	}
}