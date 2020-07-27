package com.hugman.dawn;

import com.hugman.dawn.testing.DawnBlocks;
import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.creator.CreatorHelper;
import com.hugman.dawn.util.creator.CreatorRegister;
import com.hugman.dawn.util.debug.EntryDebugWriter;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dawn implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		new DawnBlocks("oui");
		this.creatorRegisters();
	}

	private static void creatorRegisters() {
		for(CreatorRegister creatorRegister : CreatorHelper.CREATOR_REGISTERS) {
			for(Object creator : creatorRegister.getCreators()) {
				((Creator<?>)creator).register(creatorRegister);
			}
		}
	}
}
