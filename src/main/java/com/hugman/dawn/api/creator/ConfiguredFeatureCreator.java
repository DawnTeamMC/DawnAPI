package com.hugman.dawn.api.creator;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ConfiguredFeatureCreator<FC extends FeatureConfig, F extends Feature<FC>> extends SimpleCreator<ConfiguredFeature<FC, F>> {
	public ConfiguredFeatureCreator(String name, ConfiguredFeature<FC, F> feature) {
		super(BuiltinRegistries.CONFIGURED_FEATURE, name, feature);
	}
}
