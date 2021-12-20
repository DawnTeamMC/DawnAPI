package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class BSSBundle extends Bundle {
	private final BlockCreator block, stairs, slab;

	/**
	 * Creates a creator bundle containing slab and stairs variants for a block.
	 *
	 * @param builder the base builder for all the blocks
	 */
	public BSSBundle(BlockCreator.Builder builder) {
		this.block = put(builder.copy(builder.getName()).from(DefaultBlockBuilders.CUBE).build());
		this.slab = put(builder.copy(builder.getName() + "_slab").from(DefaultBlockBuilders.SLAB).build());
		this.stairs = put(builder.copy(builder.getName() + "_stairs").from(DefaultBlockBuilders.STAIRS).build());
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
