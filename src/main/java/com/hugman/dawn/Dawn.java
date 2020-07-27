package com.hugman.dawn;

import com.hugman.dawn.testing.DawnBlockPack;
import com.hugman.dawn.testing.DawnItemPack;
import com.hugman.dawn.util.pack.ModData;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dawn implements ModInitializer {
	public static final ModData MOD_DATA = new ModData("dawn");
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		new DawnItemPack(MOD_DATA);
		//new DawnBlockPack(MOD_DATA);
	}
}
