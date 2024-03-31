package fr.hugman.dawn.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;

public class MotionCommand {
    public static final String NAME = "motion";
    public static final String ADD_ARG = "add";
    public static final String SET_ARG = "set";
    public static final String TARGETS_ARG = "targets";

    // TODO: better arguments

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal(NAME).requires(sc -> sc.hasPermissionLevel(2))
                .then(CommandManager.literal(ADD_ARG)
                        .then(CommandManager.argument(TARGETS_ARG, EntityArgumentType.entities())
                                .then(CommandManager.argument("x", DoubleArgumentType.doubleArg())
                                        .then(CommandManager.argument("y", DoubleArgumentType.doubleArg())
                                                .then(CommandManager.argument("z", DoubleArgumentType.doubleArg())
                                                        .executes(cc -> setMotion(cc.getSource(), EntityArgumentType.getEntities(cc, TARGETS_ARG), DoubleArgumentType.getDouble(cc, "x"), DoubleArgumentType.getDouble(cc, "y"), DoubleArgumentType.getDouble(cc, "z"), true)))))))
                .then(CommandManager.literal(SET_ARG)
                        .then(CommandManager.argument(TARGETS_ARG, EntityArgumentType.entities())
                                .then(CommandManager.argument("x", DoubleArgumentType.doubleArg())
                                        .then(CommandManager.argument("y", DoubleArgumentType.doubleArg())
                                                .then(CommandManager.argument("z", DoubleArgumentType.doubleArg())
                                                        .executes(cc -> setMotion(cc.getSource(), EntityArgumentType.getEntities(cc, TARGETS_ARG), DoubleArgumentType.getDouble(cc, "x"), DoubleArgumentType.getDouble(cc, "y"), DoubleArgumentType.getDouble(cc, "z"), false))))))));
    }

    private static int setMotion(ServerCommandSource source, Collection<? extends Entity> targets, double x, double y, double z, boolean sum) {
        for (Entity entity : targets) {
            if (sum) {
                entity.setVelocity(entity.getVelocity().add(x, y, z));
            } else {
                entity.setVelocity(x, y, z);
            }
            if (entity instanceof ServerPlayerEntity player) {
                player.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entity));
            }
        }
        final String parameter = sum ? ADD_ARG : SET_ARG;
        if (targets.size() == 1) {
            source.sendFeedback(() -> Text.translatable("commands.motion." + parameter + ".success.single", x, y, z, targets.iterator().next().getDisplayName()), true);
        } else {
            source.sendFeedback(() -> Text.translatable("commands.motion." + parameter + ".success.multiple", x, y, z, targets.size()), true);
        }
        return targets.size();
    }
}