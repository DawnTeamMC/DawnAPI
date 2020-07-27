package com.hugman.dawn.util.creator.pack.block;

import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class MSCBlockPack extends Pack.Builder<Block> {
	protected final String prefix;
	protected final FabricBlockSettings settings;
	protected final BlockGetter[] getters;

	private final Map<BlockGetter, MCBlockPack> packMap = new HashMap<>();

	/**
	 * Creates a creator pack containing blocks having different getters as base in 16 colors.
	 *
	 * @param prefix   The prefix for the created blocks.
	 * @param settings The block settings.
	 * @param getters  The getters to use for the shapes to create.
	 */
	public MSCBlockPack(String prefix, FabricBlockSettings settings, BlockGetter... getters) {
		this.prefix = prefix;
		this.settings = settings;
		this.getters = getters;
	}

	@Override
	public Pack<Block> build() {
		for(BlockGetter getter : getters) {
			MCBlockPack pack = new MCBlockPack(prefix, getter, settings);
			packMap.put(getter, (MCBlockPack) add(pack));
		}
		return super.build();
	}

	public Block getBlock(BlockGetter getter, DyeColor color) {
		return packMap.get(getter).getBlock(color);
	}
}
