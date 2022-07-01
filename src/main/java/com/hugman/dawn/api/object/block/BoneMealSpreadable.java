package com.hugman.dawn.api.object.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

/**
 * Interface used for blocks that can be spread with bone meal.
 */
public interface BoneMealSpreadable
{
	boolean canSpreadAt(BlockView world, BlockPos pos);
}
