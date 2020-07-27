package com.hugman.dawn.util.pack.block;

import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.creator.block.BlockSettings;
import com.hugman.dawn.util.pack.Pack;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class LeavesPack {
	private final Builder leaves;
	private final BlockCreator leafPile;

	/**
	 * Creates an entry pack containing a leaves entry and its leaf pile variant.
	 *
	 * @param suffix The suffix of the leaves.
	 */
	public LeavesPack(String suffix) {
		this.leaves = new BlockCreator.Builder(suffix, BlockGetter.LEAVES, BlockSettings.LEAVES).copy(Blocks.OAK_LEAVES).build();
		this.leafPile = new BlockCreator.Builder(suffix, BlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES).build();
	}

	public Block getLeaves() {
		return leaves.getValue();
	}

	public Block getLeafPile() {
		return leafPile.getValue();
	}
}
