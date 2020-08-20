package com.hugman.dawn.init.data;

import com.hugman.dawn.Dawn;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;

public class DawnTags {
	public static final Tag<Block> BLOCK_LEAF_PILES = blockTag("leaf_piles");
	public static final Tag<Block> BLOCK_MUSHROOM_BLOCKS = blockTag("mushroom_blocks");
	public static final Tag<Block> BLOCK_MUSHROOMS = blockTag("mushrooms");
	public static final Tag<Block> BLOCK_PILES = blockTag("piles");
	public static final Tag<Block> BLOCK_VERTICAL_SLABS = blockTag("vertical_slabs");
	public static final Tag<Block> BLOCK_WOODEN_VERTICAL_SLABS = blockTag("wooden_vertical_slab");

	public static final Tag<Item> ITEM_LEAF_PILES = itemTag("leaf_piles");
	public static final Tag<Item> ITEM_MUSHROOM_BLOCKS = itemTag("mushroom_blocks");
	public static final Tag<Item> ITEM_MUSHROOMS = itemTag("mushrooms");
	public static final Tag<Item> ITEM_PILES = itemTag("piles");
	public static final Tag<Item> ITEM_VERTICAL_SLABS = itemTag("vertical_slabs");
	public static final Tag<Item> ITEM_WOODEN_VERTICAL_SLABS = itemTag("wooden_vertical_slabs");

	private static Tag<Block> blockTag(String name) {
		return TagRegistry.block(Dawn.MOD_DATA.id(name));
	}

	private static Tag<Item> itemTag(String name) {
		return TagRegistry.item(Dawn.MOD_DATA.id(name));
	}
}
