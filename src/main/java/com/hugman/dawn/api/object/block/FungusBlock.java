package com.hugman.dawn.api.object.block;

import com.hugman.dawn.api.object.RegistrableBlock;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class FungusBlock extends net.minecraft.block.FungusBlock implements RegistrableBlock {
	public FungusBlock(Settings settings, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> feature) {
		super(settings, feature);
	}
}