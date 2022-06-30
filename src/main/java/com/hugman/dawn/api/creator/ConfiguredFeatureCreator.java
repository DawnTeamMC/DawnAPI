package com.hugman.dawn.api.creator;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

/**
 * A class allowing a configured feature to be created.
 *
 * @param <FC> the feature config class, inheriting {@link FeatureConfig}
 * @param <F>  the feature class, inheriting {@link Feature}
 */
public class ConfiguredFeatureCreator<FC extends FeatureConfig, F extends Feature<FC>> extends SimpleCreator<ConfiguredFeature<FC, F>>
{

	/**
	 * Creates a configured feature.
	 *
	 * @param name    the name of the configured feature
	 * @param feature the configured feature itself
	 */
	public ConfiguredFeatureCreator(String name, ConfiguredFeature<FC, F> feature) {
		super(BuiltinRegistries.CONFIGURED_FEATURE, name, feature);
	}
}
