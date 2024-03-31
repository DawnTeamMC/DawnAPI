package fr.hugman.dawn.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Collection;

public class HealthCommand {
    public static final int PERMISSION_LEVEL = 2;
    public static final String NAME = "health";
    public static final String NAME_2 = "hp";
    public static final String ADD_ARG = "add";
    public static final String SET_ARG = "set";
    public static final String QUERY_ARG = "query";
    public static final String TARGETS_ARG = "targets";
    public static final String TARGET_ARG = "target";
    public static final String AMOUNT_AGG = "amount";

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralCommandNode<ServerCommandSource> node = dispatcher.register(
                CommandManager.literal(NAME)
                        .requires((sc) -> sc.hasPermissionLevel(PERMISSION_LEVEL))
                        .then(CommandManager.literal(ADD_ARG)
                                .then(CommandManager.argument(TARGETS_ARG, EntityArgumentType.entities()).
                                        then(CommandManager.argument(AMOUNT_AGG, FloatArgumentType.floatArg())
                                                .executes((cc) -> setHealth(cc.getSource(), EntityArgumentType.getEntities(cc, TARGETS_ARG), FloatArgumentType.getFloat(cc, AMOUNT_AGG), true)))))
                        .then(CommandManager.literal(SET_ARG)
                                .then(CommandManager.argument(TARGETS_ARG, EntityArgumentType.entities())
                                        .then(CommandManager.argument(AMOUNT_AGG, FloatArgumentType.floatArg(0.0f))
                                                .executes((cc) -> setHealth(cc.getSource(), EntityArgumentType.getEntities(cc, TARGETS_ARG), FloatArgumentType.getFloat(cc, AMOUNT_AGG), false)))))
                        .then(CommandManager.literal(QUERY_ARG)
                                .then(CommandManager.argument(TARGET_ARG, EntityArgumentType.entity())
                                        .executes((cc) -> queryHealth(cc.getSource(), EntityArgumentType.getEntity(cc, TARGET_ARG))))));
        dispatcher.register(CommandManager.literal(NAME_2).requires(source -> source.hasPermissionLevel(PERMISSION_LEVEL)).redirect(node));
    }

    private static int queryHealth(ServerCommandSource source, Entity target) {
        if (target instanceof LivingEntity livingEntity) {
            float health = livingEntity.getHealth();
            source.sendFeedback(() -> Text.translatable("commands.health.query.success", target.getDisplayName(), health), false);
            return (int) health;
        } else {
            source.sendFeedback(() -> Text.translatable("commands.health.query.failed"), false);
            return 0;
        }
    }

    private static int setHealth(ServerCommandSource source, Collection<? extends Entity> targets, float amount, boolean sum) {
        int finalTargetAmount = 0;
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity livingEntity) {
                finalTargetAmount++;
                if (sum) {
                    if (amount > 0.0F) {
                        livingEntity.heal(amount);
                    }
                } else {
                    if (livingEntity.getHealth() > 0.0F) {
                        if (amount == 0.0F) {
                            livingEntity.kill();
                        } else {
                            livingEntity.setHealth(amount);
                        }
                    }
                }
            }
        }
        final String parameter = sum ? ADD_ARG : SET_ARG;
        if (targets.size() == 1) {
            source.sendFeedback(() -> Text.translatable("commands.health." + parameter + ".success.single", amount, targets.iterator().next().getDisplayName()), true);
        } else {
            int finalTargetAmount1 = finalTargetAmount;
            source.sendFeedback(() -> Text.translatable("commands.health." + parameter + ".success.multiple", amount, finalTargetAmount1), true);
        }
        return finalTargetAmount;
    }
}