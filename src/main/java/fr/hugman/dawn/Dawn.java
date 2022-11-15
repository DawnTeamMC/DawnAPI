package fr.hugman.dawn;

import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.DataFixerBuilder;
import fr.hugman.dawn.registry.DawnCommands;
import fr.hugman.dawn.registry.DawnEntities;
import fr.hugman.dawn.registry.DawnFeatures;
import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dawn implements ModInitializer {
	public static final String MOD_ID = "dawn";
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		DawnRegistries.init();

		ShapeType.init();
		ShapeProcessorType.init();

		DawnCommands.init();
		DawnEntities.init();
		DawnFeatures.init();
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
