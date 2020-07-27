package com.hugman.dawn.util.creator.pack.block;

import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class MSBlockPack extends Pack.Builder<Block> {
	protected final String prefix;
	protected final FabricBlockSettings settings;
	protected final Block copiedBlock;
	protected final BlockGetter[] getters;

	private final Map<BlockGetter, BlockCreator> blockMap = new HashMap<>();

	/**
	 * Creates a creator pack containing blocks having different getters as base.
	 *
	 * @param prefix   The prefix for the created blocks.
	 * @param settings The block settings.
	 * @param getters  The getters to use for the shapes to create.
	 */
	public MSBlockPack(String prefix, FabricBlockSettings settings, Block copiedBlock, BlockGetter... getters) {
		this.prefix = prefix;
		this.settings = settings;
		this.copiedBlock = copiedBlock;
		this.getters = getters;
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

	@Override
	public Pack<Block> build() {
		for(BlockGetter getter : getters) {
			BlockCreator.Builder builder = new BlockCreator.Builder(prefix, getter, settings);
			if(copiedBlock != null) {
				builder.copy(copiedBlock);
			}
			blockMap.put(getter, add(builder));
		}
		return super.build();
	}

	public Block getBlock(BlockGetter getter) {
		return blockMap.get(getter).getValue();
	}
}
