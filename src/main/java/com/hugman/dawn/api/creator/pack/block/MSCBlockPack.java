package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockGetter;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class MSCBlockPack extends Pack {
	private final Map<BlockGetter, MCBlockPack> packMap = new HashMap<>();

	protected MSCBlockPack(ModData modData, String prefix, FabricBlockSettings settings, BlockGetter... getters) {
		for(BlockGetter getter : getters) {
			packMap.put(getter, add(new MCBlockPack.Builder(prefix, getter, settings), modData));
		}
	}

	public static class Builder extends Pack.Builder {
		private final String prefix;
		private final FabricBlockSettings settings;
		private final BlockGetter[] getters;

		/**
		 * Creates a creator pack containing blocks having different getters as base in 16 colors.
		 *
		 * @param name   The name for the created blocks. (ex: <code>bricks</code>)
		 * @param settings The block settings.
		 * @param getters  The getters to use for the shapes to create.
		 */
		public Builder(String name, FabricBlockSettings settings, BlockGetter... getters) {
			this.prefix = name;
			this.settings = settings;
			this.getters = getters;
		}

		public MSCBlockPack build(ModData modData) {
			return new MSCBlockPack(modData, prefix, settings, getters);
		}
	}

	public Block getBlock(BlockGetter getter, DyeColor color) {
		return packMap.get(getter).getBlock(color);
	}
}
