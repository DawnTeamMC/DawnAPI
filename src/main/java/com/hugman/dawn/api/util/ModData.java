package com.hugman.dawn.api.util;

import net.minecraft.util.Identifier;

// TODO move package
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
