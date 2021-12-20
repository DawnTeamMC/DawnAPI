package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public class BSSWBundle extends BSSBundle {
	private final BlockCreator wall;

	public BSSWBundle(String name, BlockCreator.Builder baseBuilder) {
		super(name, baseBuilder);
		this.wall = put(baseBuilder.copy(name + "_wall").from(DefaultBlockBuilders.WALL).build());
	}

	public Block getWall() {
		return wall.getValue();
	}
}
