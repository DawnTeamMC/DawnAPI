package com.hugman.dawn;

import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.creator.pack.ModdedPack;
import com.hugman.dawn.util.creator.pack.PackManager;
import net.fabricmc.api.DedicatedServerModInitializer;

public class DawnServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer() {
		creatorRegisters();
	}

	public static void creatorRegisters() {
		for(ModdedPack moddedPack : PackManager.MODDED_PACKS) {
			for(Object creator : moddedPack.getCreators()) {
				((Creator<?>) creator).serverRegister();
			}
		}
	}
}
