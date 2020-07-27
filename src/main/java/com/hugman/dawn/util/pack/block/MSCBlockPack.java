package com.hugman.dawn.util.pack.block;

import com.google.common.collect.ImmutableMap;
import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Pair;

import java.util.Map;

public class MSCBlockPack extends Pack.Builder<Block> {
	private final Map<Pair<BlockGetter, DyeColor>, Block> blockMap;

	/**
	 * Creates a creator pack containing blocks having different getters as base in 16 colors.
	 *
	 * @param prefix   The prefix for the created blocks.
	 * @param settings The block settings.
	 * @param getters  The getters to use for the shapes to create.
	 */
	public MSCBlockPack(String prefix, FabricBlockSettings settings, BlockGetter... getters) {
		ImmutableMap.Builder mapBuilder = new ImmutableMap.Builder<>();
		for(BlockGetter getter : getters) {
			MCBlockPack pack = new MCBlockPack(prefix, getter, settings);
			for(DyeColor color : DyeColor.values()) {
				mapBuilder.put(new Pair(getter, color), pack.getBlock(color));
			}
			add(pack);
		}
		blockMap = mapBuilder.build();
	}

	@Override public <P extends Pack<Block>> P build() {
		return super.build();
	}

	public Block getBlock(BlockGetter getter, DyeColor color) {
		return blockMap.get(new Pair(getter, color));
	}
}
