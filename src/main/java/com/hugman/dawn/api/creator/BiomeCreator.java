package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeCreator extends Creator {
	private final String name;
	private final Biome biome;
	private RegistryKey<Biome> registryKey;

	/**
	 * Creates a biome.
	 *
	 * @param name  The name of the biome.
	 * @param biome The biome itself.
	 */
	public BiomeCreator(String name, Biome biome) {
		this.name = name;
		this.biome = biome;
	}

	public RegistryKey<Biome> getRegistryKey() {
		return registryKey;
	}

	@Override
	public void register(ModData modData) {
		this.registryKey = RegistryKey.of(Registry.BIOME_KEY, modData.id(this.name));
		Registry.register(BuiltinRegistries.BIOME, this.registryKey.getValue(), this.biome);
	}
}
