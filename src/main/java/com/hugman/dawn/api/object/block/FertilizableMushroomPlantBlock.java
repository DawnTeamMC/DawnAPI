package com.hugman.dawn.api.object.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.Random;

public class FertilizableMushroomPlantBlock extends net.minecraft.block.MushroomPlantBlock {
	protected final Block hatBlock;

	public FertilizableMushroomPlantBlock(Settings settings, Block hatBlock) {
		super(settings, () -> Feature.HUGE_RED_MUSHROOM.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(hatBlock.getDefaultState().with(MushroomBlock.DOWN, false)), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 2)));
		this.hatBlock = hatBlock;
	}
}
