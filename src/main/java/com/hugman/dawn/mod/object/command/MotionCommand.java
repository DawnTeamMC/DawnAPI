package com.hugman.dawn.mod.object.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

import java.util.Collection;

public class MotionCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>literal("motion").requires((source) -> source.hasPermissionLevel(2))
				.then(CommandManager.literal("add")
						.then(CommandManager.argument("targets", EntityArgumentType.entities())
								.then(CommandManager.argument("x", DoubleArgumentType.doubleArg()).then(CommandManager.argument("y", DoubleArgumentType.doubleArg()).then(CommandManager.argument("z", DoubleArgumentType.doubleArg()).executes((source) -> setMotion(source.getSource(), EntityArgumentType.getEntities(source, "targets"), DoubleArgumentType.getDouble(source, "x"), DoubleArgumentType.getDouble(source, "y"), DoubleArgumentType.getDouble(source, "z"), true)))))))
				.then(CommandManager.literal("set")
						.then(CommandManager.argument("targets", EntityArgumentType.entities())
								.then(CommandManager.argument("x", DoubleArgumentType.doubleArg()).then(CommandManager.argument("y", DoubleArgumentType.doubleArg()).then(CommandManager.argument("z", DoubleArgumentType.doubleArg()).executes((source) -> setMotion(source.getSource(), EntityArgumentType.getEntities(source, "targets"), DoubleArgumentType.getDouble(source, "x"), DoubleArgumentType.getDouble(source, "y"), DoubleArgumentType.getDouble(source, "z"), false))))))));
	}

	private static int setMotion(ServerCommandSource source, Collection<? extends Entity> targets, double x, double y, double z, boolean sum) {
		for(Entity entity : targets) {
			if(sum) {
				entity.setVelocity(entity.getVelocity().add(x, y, z));
			}
			else {
				entity.setVelocity(x, y, z);
			}
			if(entity instanceof ServerPlayerEntity) {
				ServerPlayerEntity player = (ServerPlayerEntity) entity;
				player.networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entity));
			}
		}
		final String parameter;
		if(sum) {
			parameter = "add";
		}
		else {
			parameter = "set";
		}
		if(targets.size() == 1) {
			source.sendFeedback(new TranslatableText("commands.motion." + parameter + ".success.single", x, y, z, targets.iterator().next().getDisplayName()), true);
		}
		else {
			source.sendFeedback(new TranslatableText("commands.motion." + parameter + ".success.multiple", x, y, z, targets.size()), true);
		}
		return targets.size();
	}
}