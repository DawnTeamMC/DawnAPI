package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeCreator extends Creator<Biome> {
	private final Biome baseBiome;
	private final SpawnDimension spawnDimension;

	private final OverworldClimate climate;
	private final double weight;

	private final Biome.MixedNoisePoint noises;

	private BiomeCreator(String name, Biome baseBiome, SpawnDimension spawnDimension, OverworldClimate climate, double weight, Biome.MixedNoisePoint noises) {
		super(name);
		this.baseBiome = baseBiome;
		this.spawnDimension = spawnDimension;
		this.climate = climate;
		this.weight = weight;
		this.noises = noises;
	}

	@Override
	public Biome register(ModData modData) {
		value = Registry.register(BuiltinRegistries.BIOME, modData.id(name), baseBiome);
		RegistryKey<Biome> key = RegistryKey.of(Registry.BIOME_KEY, modData.id(name));
		switch(this.spawnDimension) {
			case NONE:
			default:
				break;
			case OVERWORLD_CONTINENTAL:
				OverworldBiomes.addContinentalBiome(key, climate, weight);
				break;
			case THE_NETHER:
				NetherBiomes.addNetherBiome(key, noises);
				break;
		}
		return value;
	}

	public static class Builder implements CreatorBuilder {
		private final String name;
		private final Biome baseBiome;
		private SpawnDimension spawnDimension;

		private OverworldClimate climate;
		private double weight;

		private Biome.MixedNoisePoint noises;

		/**
		 * Creates a biome.
		 *
		 * @param name      The name of the biome.
		 * @param baseBiome The biome itself.
		 */
		public Builder(String name, Biome baseBiome) {
			this.name = name;
			this.baseBiome = baseBiome;
			this.spawnDimension = SpawnDimension.NONE;
		}

		/**
		 * Adds the biome to the overworld continental generation.
		 *
		 * @param climate The biome climate.
		 * @param weight  The biome weight.
		 */
		public Builder addToOverworldContinental(OverworldClimate climate, double weight) {
			this.spawnDimension = SpawnDimension.OVERWORLD_CONTINENTAL;
			this.weight = weight;
			return this;
		}

		/**
		 * Adds the biome to the nether generation.
		 */
		public Builder addToNether(float temperature, float humidity, float altitude, float weirdness, float weight) {
			this.spawnDimension = SpawnDimension.THE_NETHER;
			this.noises = new Biome.MixedNoisePoint(temperature, humidity, altitude, weirdness, weight);
			return this;
		}

		public BiomeCreator build() {
			return new BiomeCreator(this.name, this.baseBiome, this.spawnDimension, this.climate, this.weight, this.noises);
		}
	}

	public enum SpawnDimension {
		NONE,
		OVERWORLD_CONTINENTAL,
		THE_NETHER,
		THE_END
	}
}
