package com.hugman.dawn.util.creator.pack.block;

import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class MCBlockPack extends Pack.Builder<Block> {
	protected final String prefix;
	protected final BlockGetter getter;
	protected final FabricBlockSettings settings;
	protected final ItemGroup itemGroup;
	protected final BlockCreator.Render render;

	private final Map<DyeColor, BlockCreator> blockMap = new HashMap<>();

	/**
	 * Creates a creator pack containing blocks of 16 different colors.
	 *
	 * @param getter    The getter to use to build the blocks.
	 * @param settings  The block settings.
	 * @param itemGroup The item group of the blocks.
	 * @param render    The render layer of the blocks.
	 */
	public MCBlockPack(String prefix, BlockGetter getter, FabricBlockSettings settings, ItemGroup itemGroup, BlockCreator.Render render) {
		this.prefix = prefix;
		this.getter = getter;
		this.settings = settings;
		this.itemGroup = itemGroup;
		this.render = render;
	}

	/**
	 * Creates a creator pack containing blocks of 16 different colors.
	 *
	 * @param getter    The getter to use to build the blocks.
	 * @param settings  The block settings.
	 * @param itemGroup The item group of the blocks.
	 */
	public MCBlockPack(String prefix, BlockGetter getter, FabricBlockSettings settings, ItemGroup itemGroup) {
		this(prefix, getter, settings, itemGroup, null);
	}

	/**
	 * Creates a creator pack containing blocks of 16 different colors.
	 *
	 * @param getter   The getter to use to build the blocks.
	 * @param settings The block settings.
	 */
	public MCBlockPack(String prefix, BlockGetter getter, FabricBlockSettings settings) {
		this(prefix, getter, settings, null);
	}

	@Override
	public Pack<Block> build() {
		for(DyeColor color : DyeColor.values()) {
			BlockCreator.Builder entry = new BlockCreator.Builder(color.getName() + prefix, getter, settings.materialColor(color.getMaterialColor())).setRender(render).setItemGroup(itemGroup);
			blockMap.put(color, add(entry));
		}
		return super.build();
	}

	public Block getBlock(DyeColor color) {
		return blockMap.get(color).getValue();
	}
}
