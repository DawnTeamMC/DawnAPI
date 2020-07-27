package com.hugman.dawn;

import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.creator.CreatorHelper;
import com.hugman.dawn.util.creator.CreatorRegister;
import net.fabricmc.api.DedicatedServerModInitializer;

public class DawnServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer() {
		this.creatorRegisters();
	}

	public static void creatorRegisters() {
		for(CreatorRegister creatorRegister : CreatorHelper.CREATOR_REGISTERS) {
			for(Object creator : creatorRegister.getCreators()) {
				((Creator<?>)creator).serverRegister(creatorRegister);
			}
		}
	}
}
