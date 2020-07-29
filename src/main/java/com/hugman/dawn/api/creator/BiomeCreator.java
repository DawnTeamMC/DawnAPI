package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.SimpleBuilder;
import net.fabricmc.fabric.api.biomes.v1.FabricBiomes;
import net.fabricmc.fabric.api.biomes.v1.NetherBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeCreator extends Creator<Biome> {
	private final Biome biome;
	private final SpawnDimension spawnDimension;

	private final OverworldClimate climate;
	private final double weight;
	private final boolean isSpawnBiome;

	private final Biome.MixedNoisePoint noises;

	private BiomeCreator(String name, Biome biome, SpawnDimension spawnDimension, OverworldClimate climate, double weight, boolean isSpawnBiome, Biome.MixedNoisePoint noises) {
		super(name);
		this.biome = biome;
		this.spawnDimension = spawnDimension;
		this.climate = climate;
		this.weight = weight;
		this.isSpawnBiome = isSpawnBiome;
		this.noises = noises;
	}

	@Override
	public Biome register(ModData modData) {
		value = Registry.register(BuiltinRegistries.BIOME, modData.id(name), biome);
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

	public static class Builder implements SimpleBuilder {
		private final String name;
		private final Biome biome;
		private SpawnDimension spawnDimension;

		private OverworldClimate climate;
		private double weight;
		private boolean isSpawnBiome;

		private Biome.MixedNoisePoint noises;

		/**
		 * Creates a simple biome that won't spawn in any dimension.
		 *
		 * @param name  The name of the biome.
		 * @param biome The biome itself.
		 */
		public Builder(String name, Biome biome) {
			this.name = name;
			this.biome = biome;
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
			return new BiomeCreator(this.name, this.biome, this.spawnDimension, this.climate, this.weight, this.isSpawnBiome, this.noises);
		}
	}

	public enum SpawnDimension {
		NONE,
		OVERWORLD_CONTINENTAL,
		THE_NETHER,
		THE_END
	}
}
