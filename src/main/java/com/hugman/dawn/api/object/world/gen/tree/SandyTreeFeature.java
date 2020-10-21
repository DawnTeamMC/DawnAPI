package com.hugman.dawn.api.object.world.gen.tree;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class SandyTreeFeature extends TreeFeature implements ExtendedTreeGeneration {
	public SandyTreeFeature(Codec<TreeFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean canGenerateOn(TestableWorld world, BlockPos pos) {
		return (world.testBlockState(pos, (state) -> BlockTags.SAND.contains(state.getBlock()))) || Feature.isSoil(world, pos);
	}

	@Override
	public boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return BlockTags.SAND.contains(floor.getBlock()) || floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT) || floor.isOf(Blocks.COARSE_DIRT) || floor.isOf(Blocks.PODZOL) || floor.isOf(Blocks.FARMLAND);
	}
}
