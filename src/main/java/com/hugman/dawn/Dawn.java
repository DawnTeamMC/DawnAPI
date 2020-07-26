package com.hugman.dawn;

import com.hugman.dawn.json.DataWriter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public class Dawn implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();
		for(ModContainer mod : mods) {
			new DataWriter(mod.getMetadata());
		}
	}
}
