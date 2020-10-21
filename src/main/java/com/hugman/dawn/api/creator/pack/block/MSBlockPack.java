package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.util.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.util.BlockGetter;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class MSBlockPack extends Pack {
	private final Map<BlockGetter, Block> blockMap = new HashMap<>();

	protected MSBlockPack(ModData modData, String name, FabricBlockSettings settings, BlockGetter... getters) {
		for(BlockGetter getter : getters) {
			blockMap.put(getter, add(new BlockCreator.Builder(name, getter, settings), modData));
		}
	}

	public static class Builder implements PackBuilder {
		private final String name;
		private final FabricBlockSettings settings;
		private final BlockGetter[] getters;

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

		public MSBlockPack build(ModData modData) {
			return new MSBlockPack(modData, name, settings, getters);
		}
	}

	public Block getBlock(BlockGetter getter) {
		return blockMap.get(getter);
	}
}
