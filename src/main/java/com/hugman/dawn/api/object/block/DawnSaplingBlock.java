package com.hugman.dawn.api.object.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.Predicate;

public class DawnSaplingBlock extends net.minecraft.block.SaplingBlock {
	private final Predicate<BlockState> predicate;

	public DawnSaplingBlock(AbstractBlock.Settings settings, SaplingGenerator saplingGenerator, Predicate<BlockState> predicate) {
		super(saplingGenerator, settings);
		this.predicate = predicate;
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		if(predicate != null) return predicate.test(floor);
		return super.canPlantOnTop(floor, world, pos);
	}
}