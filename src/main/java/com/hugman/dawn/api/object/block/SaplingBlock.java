package com.hugman.dawn.api.object.block;

import com.hugman.dawn.api.object.world.gen.tree.ExtendedTreeGeneration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.TestableWorld;

public class SaplingBlock extends net.minecraft.block.SaplingBlock {
	private final SaplingGenerator saplingGenerator;

	public SaplingBlock(SaplingGenerator saplingGenerator, AbstractBlock.Settings settings) {
		super(saplingGenerator, settings);
		this.saplingGenerator = saplingGenerator;
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		if(saplingGenerator instanceof ExtendedTreeGeneration) {
			return ((ExtendedTreeGeneration) this).canPlantOnTop(floor, world, pos);
		}
		else {
			return super.canPlantOnTop(floor, world, pos);
		}
	}
}