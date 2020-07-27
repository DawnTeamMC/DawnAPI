package com.hugman.dawn.util.pack.block;

import com.google.common.collect.ImmutableMap;
import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.Map;

public class MSBlockPack extends Pack.Builder<Block> {
	private final Map<BlockGetter, BlockCreator.Builder> builderMap;
	private Map<BlockGetter, BlockCreator> blockMap;

	/**
	 * Creates a creator pack containing blocks having different getters as base.
	 *
	 * @param prefix   The prefix for the created blocks.
	 * @param settings The block settings.
	 * @param getters  The getters to use for the shapes to create.
	 */
	public MSBlockPack(String prefix, FabricBlockSettings settings, BlockGetter... getters) {
		ImmutableMap.Builder mapBuilder = new ImmutableMap.Builder<DyeColor, Block>();
		for(BlockGetter getter : getters) {
			BlockCreator entry = add(new BlockCreator.Builder(prefix, getter, settings));
			mapBuilder.put(getter, entry);
		}
		builderMap = mapBuilder.build();
	}

	/**
	 * Creates a creator pack containing blocks having different getters as base.
	 *
	 * @param prefix   The prefix for the created blocks.
	 * @param settings The block settings.
	 * @param getters  The getters to use for the shapes to create.
	 */
	public MSBlockPack(String prefix, FabricBlockSettings settings, Block copiedBlock, BlockGetter... getters) {
		ImmutableMap.Builder mapBuilder = new ImmutableMap.Builder<DyeColor, Block>();
		for(BlockGetter getter : getters) {
			BlockCreator entry = add(new BlockCreator.Builder(prefix, getter, settings).copy(copiedBlock));
			mapBuilder.put(getter, entry);
		}
		builderMap = mapBuilder.build();
	}

	@Override
	public Pack<Block> build() {
		builderMap.forEach((getter, creator) -> blockMap.put(getter, add(creator)));
		return super.build();
	}

	public Block getBlock(BlockGetter getter) {
		return blockMap.get(getter).getValue();
	}
}
