package fr.hugman.dawn.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import fr.hugman.dawn.Dawn;
import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.shape.ConfiguredShape;
import net.minecraft.block.Block;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgument;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Clearable;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class ShapeCommand {
    private static final int MAX_BLOCKS = 32768;
    private static final DynamicCommandExceptionType INVALID_SHAPE_EXCEPTION = new DynamicCommandExceptionType((id) -> Text.translatable("commands.shape.fail.invalid_id", id));
    private static final SimpleCommandExceptionType TOO_COMPLEX_EXCEPTION = new SimpleCommandExceptionType(Text.translatable("commands.shape.fail.too_complex"));
    private static final Dynamic2CommandExceptionType TOO_BIG_EXCEPTION = new Dynamic2CommandExceptionType((maxCount, count) -> Text.translatable("commands.shape.fail.too_much", maxCount, count));
    public static final SuggestionProvider<ServerCommandSource> SUGGESTION_PROVIDER = (context, builder) -> CommandSource.suggestIdentifiers(DawnRegistries.CONFIGURED_SHAPE.getIds(), builder);

    public static final String NAME = "shape";
    public static final String CONFIGURED_SHAPE_ARG = "configured_shape";
    public static final String POS_ARG = "pos";
    public static final String FILL_ARG = "fill";
    public static final String BLOCK_ARG = "block";

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(CommandManager.literal(NAME).requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument(CONFIGURED_SHAPE_ARG, IdentifierArgumentType.identifier()).suggests(SUGGESTION_PROVIDER)
                        .then(CommandManager.argument(POS_ARG, BlockPosArgumentType.blockPos())
                                .then(CommandManager.literal(FILL_ARG)
                                        .then(CommandManager.argument(BLOCK_ARG, BlockStateArgumentType.blockState(registryAccess))
                                                .executes(context -> fillShape(context.getSource(), IdentifierArgumentType.getIdentifier(context, CONFIGURED_SHAPE_ARG), BlockPosArgumentType.getLoadedBlockPos(context, POS_ARG), BlockStateArgumentType.getBlockState(context, BLOCK_ARG))))))));
    }

    private static int fillShape(ServerCommandSource source, Identifier identifier, BlockPos origin, BlockStateArgument stateArgument) throws CommandSyntaxException {
        ConfiguredShape configuredShape = DawnRegistries.CONFIGURED_SHAPE.get(identifier);
        if (configuredShape == null) {
            throw INVALID_SHAPE_EXCEPTION.create(identifier);
        }
        ServerWorld world = source.getWorld();

        Shape shape = configuredShape.get(source.getWorld().getRandom());
        shape = shape.applyLayer(new TranslateLayer(Position.of(origin)));

        Dawn.LOGGER.info("Trying to stream shape");
        List<Position> positions;
        try {
            positions = shape.stream().toList();
        } catch (OutOfMemoryError e) {
            throw TOO_COMPLEX_EXCEPTION.create();
        }
        int i = positions.size();
        if (i > MAX_BLOCKS) {
            throw TOO_BIG_EXCEPTION.create(MAX_BLOCKS, i);
        }
        for (var pos : positions) {
            BlockPos blockPos = pos.toBlockPos();
            Clearable.clear(world.getBlockEntity(blockPos));
            stateArgument.setBlockState(world, blockPos, Block.NOTIFY_LISTENERS);
        }

        source.sendFeedback(() -> Text.translatable("commands.shape.fill.success", identifier, i, stateArgument.getBlockState().getBlock().getName()), true);
        return i;
    }
}