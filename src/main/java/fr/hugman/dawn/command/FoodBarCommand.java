package fr.hugman.dawn.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import fr.hugman.dawn.mixin.HungerManagerAccessor;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;

public class FoodBarCommand {
    public static final String NAME = "foodbar";
    public static final String ADD_ARG = "add";
    public static final String SET_ARG = "set";
    public static final String QUERY_ARG = "query";
    public static final String FOOD_ARG = "food";
    public static final String SATURATION_ARG = "saturation";
    public static final String TARGETS_ARG = "targets";
    public static final String TARGET_ARG = "target";
    public static final String AMOUNT_AGG = "amount";

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal(NAME).requires(sc -> sc.hasPermissionLevel(2))
                .then(CommandManager.literal(ADD_ARG)
                        .then(CommandManager.literal(FOOD_ARG)
                                .then(CommandManager.argument(TARGETS_ARG, EntityArgumentType.players())
                                        .then(CommandManager.argument(AMOUNT_AGG, IntegerArgumentType.integer())
                                                .executes(cc -> setFood(cc.getSource(), EntityArgumentType.getPlayers(cc, TARGETS_ARG), IntegerArgumentType.getInteger(cc, AMOUNT_AGG), true)))))
                        .then(CommandManager.literal(SATURATION_ARG)
                                .then(CommandManager.argument(TARGETS_ARG, EntityArgumentType.players())
                                        .then(CommandManager.argument(AMOUNT_AGG, FloatArgumentType.floatArg())
                                                .executes(cc -> setSaturation(cc.getSource(), EntityArgumentType.getPlayers(cc, TARGETS_ARG), FloatArgumentType.getFloat(cc, AMOUNT_AGG), true))))))
                .then(CommandManager.literal(SET_ARG)
                        .then(CommandManager.literal(FOOD_ARG)
                                .then(CommandManager.argument(TARGETS_ARG, EntityArgumentType.players())
                                        .then(CommandManager.argument(AMOUNT_AGG, IntegerArgumentType.integer(0, 20))
                                                .executes(cc -> setFood(cc.getSource(), EntityArgumentType.getPlayers(cc, TARGETS_ARG), IntegerArgumentType.getInteger(cc, AMOUNT_AGG), false)))))
                        .then(CommandManager.literal(SATURATION_ARG)
                                .then(CommandManager.argument(TARGETS_ARG, EntityArgumentType.players())
                                        .then(CommandManager.argument(AMOUNT_AGG, FloatArgumentType.floatArg(0.0f))
                                                .executes(cc -> setSaturation(cc.getSource(), EntityArgumentType.getPlayers(cc, TARGETS_ARG), FloatArgumentType.getFloat(cc, AMOUNT_AGG), false))))))
                .then(CommandManager.literal(QUERY_ARG)
                        .then(CommandManager.literal(FOOD_ARG)
                                .then(CommandManager.argument(TARGET_ARG, EntityArgumentType.player())
                                        .executes(cc -> queryFood(cc.getSource(), EntityArgumentType.getPlayer(cc, TARGET_ARG)))))
                        .then(CommandManager.literal(SATURATION_ARG)
                                .then(CommandManager.argument(TARGET_ARG, EntityArgumentType.player())
                                        .executes(cc -> querySaturation(cc.getSource(), EntityArgumentType.getPlayer(cc, TARGET_ARG)))))));
    }

    private static int queryFood(ServerCommandSource source, ServerPlayerEntity target) {
        int foodLevel = target.getHungerManager().getFoodLevel();
        source.sendFeedback(() -> Text.translatable("commands.foodbar.query.food", target.getDisplayName(), foodLevel), false);
        return foodLevel;
    }

    private static int querySaturation(ServerCommandSource source, ServerPlayerEntity target) {
        float saturationLevel = target.getHungerManager().getSaturationLevel();
        source.sendFeedback(() -> Text.translatable("commands.foodbar.query.saturation", target.getDisplayName(), saturationLevel), false);
        return (int) saturationLevel;
    }

    private static int setFood(ServerCommandSource source, Collection<ServerPlayerEntity> targets, int amount, boolean sum) {
        for (ServerPlayerEntity player : targets) {
            HungerManager stats = player.getHungerManager();
            stats.setFoodLevel(sum ? amount + stats.getFoodLevel() : amount);
        }
        final String parameter = sum ? ADD_ARG : SET_ARG;
        if (targets.size() == 1) {
            source.sendFeedback(() -> Text.translatable("commands.foodbar." + parameter + ".food.success.single", amount, targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendFeedback(() -> Text.translatable("commands.foodbar." + parameter + ".food.success.multiple", amount, targets.size()), true);
        }
        return targets.size();
    }

    private static int setSaturation(ServerCommandSource source, Collection<ServerPlayerEntity> targets, float amount, boolean sum) {
        for (ServerPlayerEntity entity : targets) {
            HungerManager stats = entity.getHungerManager();
            ((HungerManagerAccessor) stats).setSaturationLevel(sum ? amount + stats.getSaturationLevel() : amount);
        }
        final String parameter = sum ? ADD_ARG : SET_ARG;
        if (targets.size() == 1) {
            source.sendFeedback(() -> Text.translatable("commands.foodbar." + parameter + ".saturation.success.single", amount, targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendFeedback(() -> Text.translatable("commands.foodbar." + parameter + ".saturation.success.multiple", amount, targets.size()), true);
        }
        return targets.size();
    }
}
