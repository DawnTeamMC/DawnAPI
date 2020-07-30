package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockGetter;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class MSBlockPack extends Pack {
	private final Map<BlockGetter, Block> blockMap = new HashMap<>();

	protected MSBlockPack(ModData modData, String prefix, FabricBlockSettings settings, Block copiedBlock, BlockGetter... getters) {
		for(BlockGetter getter : getters) {
			blockMap.put(getter, add(new BlockCreator.Builder(prefix, getter, settings).copy(copiedBlock), modData));
		}
	}

	public static class Builder extends Pack.Builder {
		private final String prefix;
		private final FabricBlockSettings settings;
		private Block copiedBlock;
		private final BlockGetter[] getters;

		/**
		 * Creates a creator pack containing blocks having different getters as base.
		 *
		 * @param name     The name for the created blocks. (ex: <code>bricks</code>)
		 * @param settings The block settings.
		 * @param getters  The getters to use for the shapes to create.
		 */
		public Builder(String name, FabricBlockSettings settings, BlockGetter... getters) {
			this.prefix = name;
			this.settings = settings;
			this.copiedBlock = null;
			this.getters = getters;
		}

		/**
		 * Copies some properties from a block. (render layer, item group, flammability, cook time, composting chance)
		 */
		public Builder copy(Block copiedBlock) {
			this.copiedBlock = copiedBlock;
			return this;
		}

		public MSBlockPack build(ModData modData) {
			return new MSBlockPack(modData, prefix, settings, copiedBlock, getters);
		}
	}

	public Block getBlock(BlockGetter getter) {
		return blockMap.get(getter);
	}
}
