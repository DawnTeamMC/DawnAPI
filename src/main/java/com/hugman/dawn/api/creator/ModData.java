package com.hugman.dawn.api.creator;

import net.minecraft.util.Identifier;

public class ModData {
	private final String modName;

	public ModData(String modName) {
		this.modName = modName;
	}

	public String getModName() {
		return modName;
	}

	public Identifier id(String s) {
		return new Identifier(modName, s);
	}
}
