package com.hugman.dawn.api.object.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.Predicate;

public class SaplingBlock extends net.minecraft.block.SaplingBlock {
	private final Predicate<BlockState> predicate;

	public SaplingBlock(SaplingGenerator saplingGenerator, AbstractBlock.Settings settings) {
		super(saplingGenerator, settings);
		this.predicate = null;
	}

	public SaplingBlock(SaplingGenerator saplingGenerator, Predicate<BlockState> predicate, AbstractBlock.Settings settings) {
		super(saplingGenerator, settings);
		this.predicate = predicate;
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		if(predicate != null) {
			if(predicate.test(floor)) return true;
		}
		return super.canPlantOnTop(floor, world, pos);
	}
}