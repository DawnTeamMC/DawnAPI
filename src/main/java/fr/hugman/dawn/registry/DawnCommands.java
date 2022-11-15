package fr.hugman.dawn.registry;

import fr.hugman.dawn.command.ExportCommand;
import fr.hugman.dawn.command.FoodBarCommand;
import fr.hugman.dawn.command.HealthCommand;
import fr.hugman.dawn.command.MotionCommand;
import fr.hugman.dawn.command.ShapeCommand;
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
