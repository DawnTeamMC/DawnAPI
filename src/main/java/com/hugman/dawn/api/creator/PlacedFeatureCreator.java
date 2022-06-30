package com.hugman.dawn.api.creator;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.PlacedFeature;

/**
 * A class allowing a placed feature to be created.
 */
public class PlacedFeatureCreator extends SimpleCreator<PlacedFeature>
{

	/**
	 * Creates a placed feature.
	 *
	 * @param name          the name of the placed feature
	 * @param placedFeature the placed feature itself
	 */
	public PlacedFeatureCreator(String name, PlacedFeature placedFeature) {
		super(BuiltinRegistries.PLACED_FEATURE, name, placedFeature);
	}
}
