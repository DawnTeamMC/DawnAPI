package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.util.BlockSettings;
import com.hugman.dawn.api.util.ModData;
import net.minecraft.block.Block;

public class LeavesPack extends Pack {
	private final Block leaves;
	private final Block leafPile;

	protected LeavesPack(ModData modData, String suffix) {
		this.leaves = add(new BlockCreator.Builder(suffix, DefaultBlockGetter.LEAVES, BlockSettings.LEAVES).flammability(30, 60), modData);
		this.leafPile = add(new BlockCreator.Builder(suffix, DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).flammability(30, 60), modData);
	}

	public Block getLeaves() {
		return leaves;
	}

	public Block getLeafPile() {
		return leafPile;
	}

	public static class Builder implements Pack.Builder {
		private final String suffix;

		/**
		 * Creates an entry pack containing a leaves entry and its leaf pile variant.
		 *
		 * @param name The name of the leaves. (ex: <code>oak</code>)
		 */
		public Builder(String name) {
			this.suffix = name;
		}

		public LeavesPack build(ModData modData) {
			return new LeavesPack(modData, suffix);
		}
	}
}
