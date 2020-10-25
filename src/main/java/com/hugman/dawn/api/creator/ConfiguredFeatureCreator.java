package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ConfiguredFeatureCreator<FC extends FeatureConfig> extends Creator<ConfiguredFeature<FC, ?>> {
	private ConfiguredFeatureCreator(ModData modData, String name, ConfiguredFeature<FC, ?> feature) {
		super(modData, name, feature);
	}

	@Override
	public void register() {
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, modData.id(name), value);
	}

	public static class Builder<FC extends FeatureConfig> implements Creator.Builder<ConfiguredFeature<FC, ?>> {
		protected final String name;
		protected final ConfiguredFeature<FC, ?> feature;

		/**
		 * Creates a configured feature.
		 *
		 * @param name              The name of the configured feature.
		 * @param configuredFeature The configured feature itself.
		 */
		public Builder(String name, ConfiguredFeature<FC, ?> configuredFeature) {
			this.name = name;
			this.feature = configuredFeature;
		}

		public ConfiguredFeatureCreator build(ModData modData) {
			return new ConfiguredFeatureCreator<>(modData, this.name, this.feature);
		}
	}
}
