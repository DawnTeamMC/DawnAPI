package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeCreator extends Creator<RegistryKey<Biome>> {
	private final Biome biome;

	private BiomeCreator(ModData modData, String name, Biome biome) {
		super(modData, name, RegistryKey.of(Registry.BIOME_KEY, modData.id(name)));
		this.biome = biome;
	}

	@Override
	public void register() {
		Registry.register(BuiltinRegistries.BIOME, value.getValue(), biome);
	}

	public static class Builder implements Creator.Builder<RegistryKey<Biome>> {
		private final String name;
		private final Biome biome;

		/**
		 * Creates a biome.
		 *
		 * @param name  The name of the biome.
		 * @param biome The biome itself.
		 */
		public Builder(String name, Biome biome) {
			this.name = name;
			this.biome = biome;
		}

		public BiomeCreator build(ModData modData) {
			return new BiomeCreator(modData, this.name, this.biome);
		}
	}
}
