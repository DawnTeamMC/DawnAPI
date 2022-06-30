package com.hugman.dawn.api.creator;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

/**
 * A class allowing a feature to be created.
 *
 * @param <FC> the feature config class, inheriting {@link FeatureConfig}
 * @param <F>  the feature class, inheriting {@link Feature}
 */
public class FeatureCreator<FC extends FeatureConfig, F extends Feature<FC>> extends SimpleCreator<F>
{

	/**
	 * Creates a feature.
	 *
	 * @param name    the name of the feature
	 * @param feature the feature itself
	 */
	public FeatureCreator(String name, F feature) {
		super(Registry.FEATURE, name, feature);
	}
}
