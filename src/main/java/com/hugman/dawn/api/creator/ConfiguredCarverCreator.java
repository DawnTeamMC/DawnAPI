package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;

public class ConfiguredCarverCreator<CC extends CarverConfig> extends Creator {
	private ConfiguredCarverCreator(ModData modData, String name, ConfiguredCarver<CC> feature) {
		super(modData, name, feature);
	}

	@Override
	public void register() {
		Registry.register(BuiltinRegistries.CONFIGURED_CARVER, modData.id(name), value);
	}

	public static class Builder<CC extends CarverConfig> implements CreatorBuilder<ConfiguredCarver<CC>> {
		protected final String name;
		protected final ConfiguredCarver<CC> feature;

		/**
		 * Creates a configured carver.
		 *
		 * @param name             The name of the configured carver.
		 * @param configuredCarver The configured carver itself.
		 */
		public Builder(String name, ConfiguredCarver<CC> configuredCarver) {
			this.name = name;
			this.feature = configuredCarver;
		}

		public ConfiguredCarverCreator<CC> build(ModData modData) {
			return new ConfiguredCarverCreator<>(modData, this.name, this.feature);
		}
	}
}
