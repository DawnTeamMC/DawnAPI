package com.hugman.dawn.api.object.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class SaplingBlock extends net.minecraft.block.SaplingBlock {
	public SaplingBlock(SaplingGenerator tree, AbstractBlock.Settings settings) {
		super(tree, settings);
	}
}