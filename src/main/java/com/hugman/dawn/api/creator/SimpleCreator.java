package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.registry.Registry;

public abstract class SimpleCreator<V> extends Creator
{
	protected final String name;
	protected final Registry<?> registry;
	protected V value;

	protected SimpleCreator(Registry<?> registry, String name, V value) {
		this.name = name;
		this.value = value;
		this.registry = registry;
	}

	public V getValue() {
		return value;
	}

	public void register(ModData modData) {
		this.value = Registry.register((Registry<V>) registry, modData.id(this.name), this.value);
	}

	@Environment(EnvType.CLIENT)
	public void clientRegister(ModData modData) {

	}

	public void serverRegister(ModData modData, boolean isDedicatedServer) {

	}
}
