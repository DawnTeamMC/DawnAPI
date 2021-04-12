package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.BlockSettings;
import com.hugman.dawn.api.util.DefaultBlockTemplate;
import net.minecraft.block.Block;

import javax.annotation.Nullable;

public class LeavesBundle extends Bundle {
	private final BlockCreator leaves;
	private BlockCreator leafPile;

	protected LeavesBundle(String prefix, boolean addPile) {
		this.leaves = put(new BlockCreator.Builder(prefix, DefaultBlockTemplate.LEAVES, BlockSettings.LEAVES).flammability(30, 60).build());
		if(addPile) this.leafPile = put(new BlockCreator.Builder(prefix, DefaultBlockTemplate.LEAF_PILE, BlockSettings.LEAF_PILE).flammability(30, 60).build());
	}

	public Block getLeaves() {
		return leaves.getValue();
	}

	@Nullable
	public Block getLeafPile() {
		return leafPile.getValue();
	}

	public static class Builder {
		private final String suffix;
		private boolean addPile;

		/**
		 * Creates an entry pack containing a leaves entry and its leaf pile variant.
		 *
		 * @param name The name of the leaves. (ex: <code>oak</code>)
		 */
		public Builder(String name) {
			this.suffix = name;
		}

		public Builder pile(boolean addPile) {
			this.addPile = addPile;
			return this;
		}

		public LeavesBundle build() {
			return new LeavesBundle(suffix, addPile);
		}
	}
}
