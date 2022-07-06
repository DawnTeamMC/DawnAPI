package com.hugman.dawn.mod.object.command;

import com.hugman.dawn.api.DawnRegistries;
import com.hugman.dawn.api.init.shape.ConfiguredShape;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import net.minecraft.block.Block;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgument;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.command.argument.RegistryKeyArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;

import java.util.List;

public class ShapeCommand {
	public static final String NAME = "shape";
	public static final String SHAPE_ARG = "shape";
	public static final String ORIGIN_ARG = "origin";
	public static final String FILL_ARG = "fill";
	public static final String STATE_ARG = "state";

	private static final DynamicCommandExceptionType INVALID_SHAPE_EXCEPTION = new DynamicCommandExceptionType((id) -> Text.translatable("commands.shape.shape.invalid", id));

	public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
		dispatcher.register(CommandManager.literal(NAME).requires(sc -> sc.hasPermissionLevel(2))
				.then(CommandManager.argument(SHAPE_ARG, RegistryKeyArgumentType.registryKey(DawnRegistries.CONFIGURED_SHAPE.getKey()))
						.then(CommandManager.argument(ORIGIN_ARG, BlockPosArgumentType.blockPos())
								.then(CommandManager.literal(FILL_ARG)
										.then(CommandManager.argument(STATE_ARG, BlockStateArgumentType.blockState(registryAccess))
												.executes(cc -> fillShape(cc.getSource(), getShapeEntry(cc, SHAPE_ARG), BlockPosArgumentType.getLoadedBlockPos(cc, ORIGIN_ARG), BlockStateArgumentType.getBlockState(cc, STATE_ARG))))))));
	}

	private static int fillShape(ServerCommandSource source, RegistryEntry<ConfiguredShape> configuredShapeEntry, BlockPos origin, BlockStateArgument stateArgument) throws CommandSyntaxException {
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

	public static RegistryEntry<ConfiguredShape> getShapeEntry(CommandContext<ServerCommandSource> context, String name) throws CommandSyntaxException {
		return RegistryKeyArgumentType.getRegistryEntry(context, name, (RegistryKey<Registry<ConfiguredShape>>) DawnRegistries.CONFIGURED_SHAPE.getKey(), INVALID_SHAPE_EXCEPTION);
	}
}