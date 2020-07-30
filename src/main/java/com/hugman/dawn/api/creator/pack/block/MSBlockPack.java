package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.util.BlockGetter;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class MSBlockPack extends Pack {
	private final Map<BlockGetter, Block> blockMap = new HashMap<>();

	protected MSBlockPack(ModData modData, String name, FabricBlockSettings settings, Block copiedBlock, BlockGetter... getters) {
		for(BlockGetter getter : getters) {
			blockMap.put(getter, add(new BlockCreator.Builder(name, getter, settings).copy(copiedBlock), modData));
		}
	}

	public static class Builder implements PackBuilder {
		private final String name;
		private final FabricBlockSettings settings;
		private Block copiedBlock;
		private final BlockGetter[] getters;

		/**
		 * Creates a creator pack containing blocks having different getters as base.
		 *
		 * @param name        The name for the created blocks. (ex: <code>bricks</code>)
		 * @param copiedBlock The block to be copied.
		 * @param getters     The getters to use for the shapes to create.
		 */
		public Builder(String name, Block copiedBlock, BlockGetter... getters) {
			this(name, FabricBlockSettings.copyOf(copiedBlock), getters);
			copy(copiedBlock);
		}

		/**
		 * Creates a creator pack containing blocks having different getters as base.
		 *
		 * @param name     The name for the created blocks. (ex: <code>bricks</code>)
		 * @param settings The block settings.
		 * @param getters  The getters to use for the shapes to create.
		 */
		public Builder(String name, FabricBlockSettings settings, BlockGetter... getters) {
			this.name = name;
			this.settings = settings;
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
			return new MSBlockPack(modData, name, settings, copiedBlock, getters);
		}
	}

	public Block getBlock(BlockGetter getter) {
		return blockMap.get(getter);
	}
}
