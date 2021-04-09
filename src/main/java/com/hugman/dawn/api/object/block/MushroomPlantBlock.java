package com.hugman.dawn.api.object.block;

import com.hugman.dawn.api.object.RegistrableBlock;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Supplier;

public class MushroomPlantBlock extends net.minecraft.block.MushroomPlantBlock implements RegistrableBlock {
	public MushroomPlantBlock(Settings settings, Supplier<ConfiguredFeature<?, ?>> feature) {
		super(settings, feature);
	}
}