package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockGetter;
import com.hugman.dawn.api.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class MSCBlockPack extends Pack<Block> {
	private final Map<BlockGetter, MCBlockPack> packMap = new HashMap<>();

	/**
	 * Creates a creator pack containing blocks having different getters as base in 16 colors.
	 *
	 * @param prefix   The prefix for the created blocks.
	 * @param settings The block settings.
	 * @param getters  The getters to use for the shapes to create.
	 */
	public MSCBlockPack(String prefix, FabricBlockSettings settings, BlockGetter... getters) {
		for(BlockGetter getter : getters) {
			MCBlockPack pack = new MCBlockPack(prefix, getter, settings);
			packMap.put(getter, add(pack));
		}
	}

	public Block getBlock(BlockGetter getter, DyeColor color) {
		return packMap.get(getter).getBlock(color);
	}
}
