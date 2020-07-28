package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockGetter;
import com.hugman.dawn.api.creator.BlockSettings;
import com.hugman.dawn.api.creator.pack.Pack;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class LeavesPack extends Pack<Block> {
	private final BlockCreator leaves;
	private final BlockCreator leafPile;

	/**
	 * Creates an entry pack containing a leaves entry and its leaf pile variant.
	 *
	 * @param suffix The suffix of the leaves.
	 */
	public LeavesPack(String suffix) {
		leaves = add(new BlockCreator.Builder(suffix, BlockGetter.LEAVES, BlockSettings.LEAVES).copy(Blocks.OAK_LEAVES));
		leafPile = add(new BlockCreator.Builder(suffix, BlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	}

	public Block getLeaves() {
		return leaves.getValue();
	}

	public Block getLeafPile() {
		return leafPile.getValue();
	}
}
