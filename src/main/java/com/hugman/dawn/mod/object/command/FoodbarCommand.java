package com.hugman.dawn.mod.object.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

import java.util.Collection;

public class FoodbarCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(LiteralArgumentBuilder.<ServerCommandSource>literal("foodbar").requires((source) -> source.hasPermissionLevel(2))
																						  .then(CommandManager.literal("add")
																											  .then(CommandManager.literal("food").then(CommandManager.argument("targets", EntityArgumentType.players()).then(CommandManager.argument("amount", IntegerArgumentType.integer()).executes((source) -> setFood(source.getSource(), EntityArgumentType.getPlayers(source, "targets"), IntegerArgumentType.getInteger(source, "amount"), true)))))
																											  .then(CommandManager.literal("saturation").then(CommandManager.argument("targets", EntityArgumentType.players()).then(CommandManager.argument("amount", FloatArgumentType.floatArg()).executes((source) -> setSaturation(source.getSource(), EntityArgumentType.getPlayers(source, "targets"), FloatArgumentType.getFloat(source, "amount"), true))))))
																						  .then(CommandManager.literal("set")
																											  .then(CommandManager.literal("food").then(CommandManager.argument("targets", EntityArgumentType.players()).then(CommandManager.argument("amount", IntegerArgumentType.integer(0, 20)).executes((source) -> setFood(source.getSource(), EntityArgumentType.getPlayers(source, "targets"), IntegerArgumentType.getInteger(source, "amount"), false)))))
																											  .then(CommandManager.literal("saturation").then(CommandManager.argument("targets", EntityArgumentType.players()).then(CommandManager.argument("amount", FloatArgumentType.floatArg(0.0f)).executes((source) -> setSaturation(source.getSource(), EntityArgumentType.getPlayers(source, "targets"), FloatArgumentType.getFloat(source, "amount"), false)))))));
	}

	private static int setFood(ServerCommandSource source, Collection<ServerPlayerEntity> targets, int amount, boolean sum) {
		for(ServerPlayerEntity entity : targets) {
			HungerManager stats = entity.getHungerManager();
			if(sum) {
				stats.setFoodLevel(amount + stats.getFoodLevel());
			}
			else {
				stats.setFoodLevel(amount);
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
			source.sendFeedback(new TranslatableText("commands.foodbar." + parameter + ".food.success.single", amount, targets.iterator().next().getDisplayName()), true);
		}
		else {
			source.sendFeedback(new TranslatableText("commands.foodbar." + parameter + ".food.success.multiple", amount, targets.size()), true);
		}
		return targets.size();
	}

	private static int setSaturation(ServerCommandSource source, Collection<ServerPlayerEntity> targets, float amount, boolean sum) {
		for(ServerPlayerEntity entity : targets) {
			HungerManager stats = entity.getHungerManager();
			if(sum) {
				stats.setSaturationLevelClient(amount + stats.getFoodLevel());
			}
			else {
				stats.setSaturationLevelClient(amount);
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
			source.sendFeedback(new TranslatableText("commands.foodbar." + parameter + ".saturation.success.single", amount, targets.iterator().next().getDisplayName()), true);
		}
		else {
			source.sendFeedback(new TranslatableText("commands.foodbar." + parameter + ".saturation.success.multiple", amount, targets.size()), true);
		}
		return targets.size();
	}
}