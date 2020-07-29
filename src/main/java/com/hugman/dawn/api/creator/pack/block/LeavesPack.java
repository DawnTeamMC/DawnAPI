package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockGetter;
import com.hugman.dawn.api.creator.BlockSettings;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class LeavesPack extends Pack {
	private final Block leaves;
	private final Block leafPile;

	protected LeavesPack(ModData modData, String suffix) {
		this.leaves = add(new BlockCreator.Builder(suffix, BlockGetter.LEAVES, BlockSettings.LEAVES).copy(Blocks.OAK_LEAVES), modData);
		this.leafPile = add(new BlockCreator.Builder(suffix, BlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES), modData);
	}

	public static class Builder extends Pack.Builder {
		private final String suffix;

		/**
		 * Creates an entry pack containing a leaves entry and its leaf pile variant.
		 *
		 * @param suffix The suffix of the leaves.
		 */
		public Builder(String suffix) {
			this.suffix = suffix;
		}

		public LeavesPack build(ModData modData) {
			return new LeavesPack(modData, suffix);
		}
	}

	public Block getLeaves() {
		return leaves;
	}

	public Block getLeafPile() {
		return leafPile;
	}
}
