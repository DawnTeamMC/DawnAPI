package com.hugman.dawn.util.pack;

import net.minecraft.util.Identifier;

public class ModData {
	public final String modName;

	public ModData(String modName) {
		this.modName = modName;
	}

	public Identifier id(String s) {
		return new Identifier(modName, s);
	}
}
