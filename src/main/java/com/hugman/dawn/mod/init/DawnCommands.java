package com.hugman.dawn.mod.init;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.mod.object.command.FoodBarCommand;
import com.hugman.dawn.mod.object.command.HealthCommand;
import com.hugman.dawn.mod.object.command.MotionCommand;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class DawnCommands {
	public static void init() {
		if(Dawn.CONFIG.features.healthCommand) {
			CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> HealthCommand.register(dispatcher));
		}
		if(Dawn.CONFIG.features.foodbarCommand) {
			CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> FoodBarCommand.register(dispatcher));
		}
		if(Dawn.CONFIG.features.motionCommand) {
			CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> MotionCommand.register(dispatcher));
		}
	}
}
