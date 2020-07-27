package com.hugman.dawn;

import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.creator.CreatorHelper;
import com.hugman.dawn.util.creator.CreatorRegister;
import net.fabricmc.api.ClientModInitializer;

public class DawnClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		this.creatorRegisters();
	}

	public static void creatorRegisters() {
		for(CreatorRegister creatorRegister : CreatorHelper.CREATOR_REGISTERS) {
			for(Object creator : creatorRegister.getCreators()) {
				((Creator<?>)creator).clientRegister(creatorRegister);
			}
		}
	}
}
