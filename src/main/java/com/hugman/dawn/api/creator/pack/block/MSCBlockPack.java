package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.util.BlockGetter;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class MSCBlockPack extends Pack {
	private final Map<BlockGetter, MCBlockPack> packMap = new HashMap<>();

	protected MSCBlockPack(ModData modData, String prefix, FabricBlockSettings settings, Block copiedBlock, BlockGetter... getters) {
		for(BlockGetter getter : getters) {
			packMap.put(getter, add(new MCBlockPack.Builder(prefix, getter, settings).copy(copiedBlock), modData));
		}
	}

	public static class Builder implements PackBuilder {
		private final String prefix;
		private final FabricBlockSettings settings;
		private Block copiedBlock;
		private final BlockGetter[] getters;

		/**
		 * Creates a creator pack containing blocks having different getters as base in 16 colors.
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
		 * Creates a creator pack containing blocks having different getters as base in 16 colors.
		 *
		 * @param name     The name for the created blocks. (ex: <code>bricks</code>)
		 * @param settings The block settings.
		 * @param getters  The getters to use for the shapes to create.
		 */
		public Builder(String name, FabricBlockSettings settings, BlockGetter... getters) {
			this.prefix = name;
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

		public MSCBlockPack build(ModData modData) {
			return new MSCBlockPack(modData, prefix, settings, copiedBlock, getters);
		}
	}

	public Block getBlock(BlockGetter getter, DyeColor color) {
		return packMap.get(getter).getBlock(color);
	}
}
