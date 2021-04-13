package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeCreator extends Creator<RegistryKey<Biome>> {
	private final String name;
	private final Biome biome;

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

	@Override
	public void register(ModData modData) {
		this.value = RegistryKey.of(Registry.BIOME_KEY, modData.id(this.name));
		Registry.register(BuiltinRegistries.BIOME, this.value.getValue(), this.biome);
	}
}
