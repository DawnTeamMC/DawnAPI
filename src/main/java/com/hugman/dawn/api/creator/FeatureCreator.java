package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class FeatureCreator<FC extends FeatureConfig, F extends Feature<FC>> extends SimpleCreator<F> {
	public FeatureCreator(ModData modData, String name, F feature) {
		super(Registry.FEATURE, name, feature);
	}
}
