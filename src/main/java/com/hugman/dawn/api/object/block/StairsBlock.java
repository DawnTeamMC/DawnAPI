package com.hugman.dawn.api.object.block;

import net.minecraft.block.Blocks;

public class StairsBlock extends net.minecraft.block.StairsBlock {
	public StairsBlock(Settings settings) {
		super(Blocks.STONE.getDefaultState(), settings);
	}
}
