package com.hugman.dawn.api.creator.pack;

public abstract class ModdedPack extends Pack {
	public ModdedPack() {
		PackManager.MODDED_PACKS.add(this);
	}
}
