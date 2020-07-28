package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockGetter;
import com.hugman.dawn.api.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class MSBlockPack extends Pack<Block> {
	private final Map<BlockGetter, BlockCreator> blockMap = new HashMap<>();

	/**
	 * Creates a creator pack containing blocks having different getters as base.
	 *
	 * @param prefix   The prefix for the created blocks.
	 * @param settings The block settings.
	 * @param getters  The getters to use for the shapes to create.
	 */
	public MSBlockPack(String prefix, FabricBlockSettings settings, Block copiedBlock, BlockGetter... getters) {
		for(BlockGetter getter : getters) {
			BlockCreator.Builder builder = new BlockCreator.Builder(prefix, getter, settings);
			if(copiedBlock != null) {
				builder.copy(copiedBlock);
			}
			blockMap.put(getter, add(builder));
		}
	}

	/**
	 * Creates a creator pack containing blocks having different getters as base.
	 *
	 * @param prefix   The prefix for the created blocks.
	 * @param settings The block settings.
	 * @param getters  The getters to use for the shapes to create.
	 */
	public MSBlockPack(String prefix, FabricBlockSettings settings, BlockGetter... getters) {
		this(prefix, settings, null, getters);
	}

	public Block getBlock(BlockGetter getter) {
		return blockMap.get(getter).getValue();
	}
}
