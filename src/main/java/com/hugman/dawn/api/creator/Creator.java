package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public abstract class Creator {
	public void register(ModData modData) {
	}

	public void postRegister(ModData modData) {
	}

	@Environment(EnvType.CLIENT)
	public void clientRegister(ModData modData) {

	}

	public void serverRegister(ModData modData, boolean isDedicatedServer) {

	}
}
