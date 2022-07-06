package com.hugman.dawn.mod.init;

import com.hugman.dawn.mod.object.command.ExportCommand;
import com.hugman.dawn.mod.object.command.FoodBarCommand;
import com.hugman.dawn.mod.object.command.HealthCommand;
import com.hugman.dawn.mod.object.command.MotionCommand;
import com.hugman.dawn.mod.object.command.ShapeCommand;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class DawnCommands {
	public static void init() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			HealthCommand.register(dispatcher);
			FoodBarCommand.register(dispatcher);
			MotionCommand.register(dispatcher);
			ShapeCommand.register(dispatcher, registryAccess);
			if(environment.integrated)
				ExportCommand.register(dispatcher);
		});
	}
}
