package com.hugman.dawn.mod.init;

import com.hugman.dawn.mod.object.command.FoodbarCommand;
import com.hugman.dawn.mod.object.command.HealthCommand;
import com.hugman.dawn.mod.object.command.MotionCommand;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class DawnCommands {
	public static void init() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> HealthCommand.register(dispatcher));
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> MotionCommand.register(dispatcher));
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> FoodbarCommand.register(dispatcher));
	}
}
