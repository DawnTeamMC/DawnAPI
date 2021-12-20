package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class BSSBundle extends Bundle {
	private final BlockCreator block;
	private final BlockCreator stairs;
	private final BlockCreator slab;

	public BSSBundle(String name, AbstractBlock.Settings settings) {
		BlockCreator.Builder builder = new BlockCreator.Builder().settings(settings);
		this.block = put(builder.copy(name).from(DefaultBlockBuilders.CUBE).build());
		this.slab = put(builder.copy(name + "_slab").from(DefaultBlockBuilders.SLAB).build());
		this.stairs = put(builder.copy(name + "_stairs").from(DefaultBlockBuilders.STAIRS).build());
	}

	public Block getBlock() {
		return block.getValue();
	}

	public Block getSlab() {
		return slab.getValue();
	}

	public Block getStairs() {
		return stairs.getValue();
	}
}
