package com.hugman.dawn.util.creator.biome;

import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.pack.ModData;
import com.hugman.dawn.util.pack.ModPack;
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
import net.fabricmc.fabric.api.biomes.v1.NetherBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeCreator extends Creator<Biome> {
	private final Biome baseBiome;
	private final SpawnDimension spawnDimension;

	private final OverworldClimate climate;
	private final double weight;
	private final boolean isSpawnBiome;

	private final Biome.MixedNoisePoint noises;

	private BiomeCreator(String name, Biome baseBiome, SpawnDimension spawnDimension, OverworldClimate climate, double weight, boolean isSpawnBiome, Biome.MixedNoisePoint noises) {
		super(name);
		this.baseBiome = baseBiome;
		this.spawnDimension = spawnDimension;
		this.climate = climate;
		this.weight = weight;
		this.isSpawnBiome = isSpawnBiome;
		this.noises = noises;
	}

	@Override
	public Biome register(ModData modData) {
		value = Registry.register(BuiltinRegistries.BIOME, modData.id(name), baseBiome);
		switch(this.spawnDimension) {
			case NONE:
			default:
				break;
			case OVERWORLD_CONTINENTAL:
				OverworldBiomes.addContinentalBiome(value, climate, weight);
				if(isSpawnBiome) {
					FabricBiomes.addSpawnBiome(value);
				}
				break;
			case THE_NETHER:
				NetherBiomes.addNetherBiome(value, noises);
				break;
		}
		return value;
	}

	public static class Builder {
		private final String name;
		private final Biome baseBiome;
		private SpawnDimension spawnDimension;

		private OverworldClimate climate;
		private double weight;
		private boolean isSpawnBiome;

		private Biome.MixedNoisePoint noises;

		/**
		 * Creates a simple biome that won't spawn in any dimension.
		 *
		 * @param name      The name of the biome.
		 * @param baseBiome The biome itself.
		 */
		public Builder(String name, Biome baseBiome) {
			this.name = name;
			this.baseBiome = baseBiome;
			this.spawnDimension = SpawnDimension.NONE;
			this.climate = null;
			this.weight = 0D;
			this.isSpawnBiome = false;
			this.noises = null;
		}

		/**
		 * Adds the biome to the overworld continental generation.
		 *
		 * @param climate      The biome climate.
		 * @param weight       The biome weight.
		 * @param isSpawnBiome Defines if the player should be able to naturally spawn in the biome.
		 */
		public Builder addToOverworldContinental(OverworldClimate climate, double weight, boolean isSpawnBiome) {
			this.spawnDimension = SpawnDimension.OVERWORLD_CONTINENTAL;
			this.climate = climate;
			this.weight = weight;
			this.isSpawnBiome = isSpawnBiome;
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

		/**
		 * Builds the entry and registers the biome with all its settings.
		 */
		public BiomeCreator build() {
			return new BiomeCreator(this.name, this.baseBiome, this.spawnDimension, this.climate, this.weight, this.isSpawnBiome, this.noises);
		}
	}

	public enum SpawnDimension {
		NONE,
		OVERWORLD_CONTINENTAL,
		THE_NETHER,
		THE_END
	}
}
