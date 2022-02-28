package com.hugman.dawn.api.object.block;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class FungusBlock extends net.minecraft.block.FungusBlock {
	public FungusBlock(Settings settings, Supplier<RegistryEntry<ConfiguredFeature<HugeFungusFeatureConfig, ?>>> feature) {
		super(settings, feature);
	}
}