package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public abstract class Creator<V> {
	protected final ModData modData;
	protected final String name;
	protected final V value;

	protected Creator(ModData modData, String name, V value) {
		this.modData = modData;
		this.name = name;
		this.value = value;
	}

	public void register() {
	}

	@Environment(EnvType.CLIENT)
	public void clientRegister() {
	}

	public void serverRegister(boolean isDedicated) {
	}

	public ModData getModData() {
		return modData;
	}

	public String getName() {
		return name;
	}

	public V getValue() {
		return value;
	}
}
