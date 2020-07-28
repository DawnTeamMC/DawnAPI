package com.hugman.dawn.api.object.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class SaplingBlock extends net.minecraft.block.SaplingBlock {
	/* Extension for internal publicity */
	public SaplingBlock(SaplingGenerator tree, AbstractBlock.Settings settings) {
		super(tree, settings);
	}
}