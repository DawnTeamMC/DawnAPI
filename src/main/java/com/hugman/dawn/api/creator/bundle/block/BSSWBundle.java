package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class BSSWBundle extends BSSBundle {
	private final BlockCreator wall;

	/**
	 * Bundle builder that contains slab, stairs and wall variants for a block.
	 *
	 * @param builder the base builder for all the blocks
	 */
	public BSSWBundle(BlockCreator.Builder builder) {
		super(builder);
		this.wall = put(builder.copy(builder.getName() + "_wall").from(DefaultBlockBuilders.WALL).build());
	}

	public Block getWall() {
		return wall.getValue();
	}
}
