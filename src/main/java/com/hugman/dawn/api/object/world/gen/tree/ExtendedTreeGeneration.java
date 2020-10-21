package com.hugman.dawn.api.object.world.gen.tree;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.TestableWorld;

public interface ExtendedTreeGeneration {
	boolean canGenerateOn(TestableWorld world, BlockPos pos);
	boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos);
}
