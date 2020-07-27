package com.hugman.dawn.util.pack.block;

import com.google.common.collect.ImmutableMap;
import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;

import java.util.Map;

public class MCBlockPack extends Pack<Block> {
	private final Map<DyeColor, BlockCreator> blockMap;

	/**
	 * Creates a creator pack containing blocks of 16 different colors.
	 *
	 * @param getter    The getter to use to build the blocks.
	 * @param settings  The block settings.
	 * @param itemGroup The item group of the blocks.
	 * @param render    The render layer of the blocks.
	 */
	public MCBlockPack(String prefix, BlockGetter getter, FabricBlockSettings settings, ItemGroup itemGroup, BlockCreator.Render render) {
		ImmutableMap.Builder mapBuilder = new ImmutableMap.Builder<DyeColor, Block>();
		for(DyeColor color : DyeColor.values()) {
			BlockCreator entry = add(new BlockCreator.Builder(color.getName() + prefix, getter, settings.materialColor(color.getMaterialColor())).setRender(render).setItemGroup(itemGroup).build());
			mapBuilder.put(color, entry);
		}
		blockMap = mapBuilder.build();
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

	public Block getBlock(DyeColor color) {
		return blockMap.get(color).getValue();
	}
}
