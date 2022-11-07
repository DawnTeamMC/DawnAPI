package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SerializableRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

/**
 * A class allowing a biome to be created.
 */
public class BiomeCreator extends Creator
{
	private final String name;
	private final Biome biome;
	private RegistryKey<Biome> registryKey;

	/**
	 * Creates a biome.
	 *
	 * @param name  the name of the biome
	 * @param biome the biome itself
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
		var register = SerializableRegistries.REGISTRIES.get(registryKey);
		var codec = register.networkCodec();
		//BiomeKeys.register(this.name);
	}
}
