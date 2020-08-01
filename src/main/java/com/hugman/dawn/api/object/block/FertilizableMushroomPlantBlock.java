package com.hugman.dawn.api.object.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.Random;

public class FertilizableMushroomPlantBlock extends net.minecraft.block.MushroomPlantBlock {
	protected final Block hatBlock;

	public FertilizableMushroomPlantBlock(AbstractBlock.Settings settings, Block hatBlock) {
		super(settings);
		this.hatBlock = hatBlock;
	}

	@Override
	public boolean trySpawningBigMushroom(ServerWorld serverWorld, BlockPos pos, BlockState state, Random random) {
		serverWorld.removeBlock(pos, false);
		ConfiguredFeature hugeMushroomFeature = Feature.HUGE_RED_MUSHROOM.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(this.hatBlock.getDefaultState().with(MushroomBlock.DOWN, false)), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 2));
		if(hugeMushroomFeature.generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(), random, pos)) {
			return true;
		}
		else {
			serverWorld.setBlockState(pos, state, 3);
			return false;
		}
	}
}
