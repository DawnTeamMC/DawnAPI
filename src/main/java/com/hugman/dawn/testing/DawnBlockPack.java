package com.hugman.dawn.testing;

import com.hugman.dawn.util.creator.block.BlockCreator.Builder;
import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.pack.Pack;
import com.hugman.dawn.util.pack.ModData;
import com.hugman.dawn.util.pack.block.NormalWoodPack;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.item.Item;

public class DawnBlockPack extends Pack<Block> {
	public DawnBlockPack(ModData modData) {
		super(modData);
	}

	public final Block OAK_VERTICAL_SLAB = register(new Builder("oak", BlockGetter.VERTICAL_SLAB, Blocks.OAK_SLAB).build());
	public final Block SPRUCE_VERTICAL_SLAB = register(new Builder("spruce", BlockGetter.VERTICAL_SLAB, Blocks.SPRUCE_SLAB).build());
	public final Block BIRCH_VERTICAL_SLAB = register(new Builder("birch", BlockGetter.VERTICAL_SLAB, Blocks.BIRCH_SLAB).build());
	public final Block JUNGLE_VERTICAL_SLAB = register(new Builder("jungle", BlockGetter.VERTICAL_SLAB, Blocks.JUNGLE_SLAB).build());
	public final Block ACACIA_VERTICAL_SLAB = register(new Builder("acacia", BlockGetter.VERTICAL_SLAB, Blocks.ACACIA_SLAB).build());
	public final Block DARK_OAK_VERTICAL_SLAB = register(new Builder("dark_oak", BlockGetter.VERTICAL_SLAB, Blocks.DARK_OAK_SLAB).build());
	public final Block CRIMSON_VERTICAL_SLAB = register(new Builder("crimson", BlockGetter.VERTICAL_SLAB, Blocks.CRIMSON_SLAB).build());
	public final Block WARPED_VERTICAL_SLAB = register(new Builder("warped", BlockGetter.VERTICAL_SLAB, Blocks.WARPED_SLAB).build());
	public final Block STONE_VERTICAL_SLAB = register(new Builder("stone", BlockGetter.VERTICAL_SLAB, Blocks.STONE_SLAB).build());
	public final Block SMOOTH_STONE_VERTICAL_SLAB = register(new Builder("smooth_stone", BlockGetter.VERTICAL_SLAB, Blocks.SMOOTH_STONE_SLAB).build());
	public final Block COBBLESTONE_VERTICAL_SLAB = register(new Builder("cobblestone", BlockGetter.VERTICAL_SLAB, Blocks.COBBLESTONE_SLAB).build());
	public final Block MOSSY_COBBLESTONE_VERTICAL_SLAB = register(new Builder("mossy_cobblestone", BlockGetter.VERTICAL_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB).build());
	public final Block STONE_BRICK_VERTICAL_SLAB = register(new Builder("stone_brick", BlockGetter.VERTICAL_SLAB, Blocks.STONE_BRICK_SLAB).build());
	public final Block MOSSY_STONE_BRICK_VERTICAL_SLAB = register(new Builder("mossy_stone_brick", BlockGetter.VERTICAL_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB).build());
	public final Block GRANITE_VERTICAL_SLAB = register(new Builder("granite", BlockGetter.VERTICAL_SLAB, Blocks.GRANITE_SLAB).build());
	public final Block POLISHED_GRANITE_VERTICAL_SLAB = register(new Builder("polished_granite", BlockGetter.VERTICAL_SLAB, Blocks.POLISHED_GRANITE_SLAB).build());
	public final Block DIORITE_VERTICAL_SLAB = register(new Builder("diorite", BlockGetter.VERTICAL_SLAB, Blocks.DIORITE_SLAB).build());
	public final Block POLISHED_DIORITE_VERTICAL_SLAB = register(new Builder("polished_diorite", BlockGetter.VERTICAL_SLAB, Blocks.POLISHED_DIORITE_SLAB).build());
	public final Block ANDESITE_VERTICAL_SLAB = register(new Builder("andesite", BlockGetter.VERTICAL_SLAB, Blocks.ANDESITE_SLAB).build());
	public final Block POLISHED_ANDESITE_VERTICAL_SLAB = register(new Builder("polished_andesite", BlockGetter.VERTICAL_SLAB, Blocks.POLISHED_ANDESITE_SLAB).build());
	public final Block BRICK_VERTICAL_SLAB = register(new Builder("brick", BlockGetter.VERTICAL_SLAB, Blocks.BRICK_SLAB).build());
	public final Block SANDSTONE_VERTICAL_SLAB = register(new Builder("sandstone", BlockGetter.VERTICAL_SLAB, Blocks.SANDSTONE_SLAB).build());
	public final Block CUT_SANDSTONE_VERTICAL_SLAB = register(new Builder("cut_sandstone", BlockGetter.VERTICAL_SLAB, Blocks.CUT_SANDSTONE_SLAB).build());
	public final Block SMOOTH_SANDSTONE_VERTICAL_SLAB = register(new Builder("smooth_sandstone", BlockGetter.VERTICAL_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB).build());
	public final Block RED_SANDSTONE_VERTICAL_SLAB = register(new Builder("red_sandstone", BlockGetter.VERTICAL_SLAB, Blocks.RED_SANDSTONE_SLAB).build());
	public final Block CUT_RED_SANDSTONE_VERTICAL_SLAB = register(new Builder("cut_red_sandstone", BlockGetter.VERTICAL_SLAB, Blocks.CUT_RED_SANDSTONE_SLAB).build());
	public final Block SMOOTH_RED_SANDSTONE_VERTICAL_SLAB = register(new Builder("smooth_red_sandstone", BlockGetter.VERTICAL_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB).build());
	public final Block NETHER_BRICK_VERTICAL_SLAB = register(new Builder("nether_brick", BlockGetter.VERTICAL_SLAB, Blocks.NETHER_BRICK_SLAB).build());
	public final Block RED_NETHER_BRICK_VERTICAL_SLAB = register(new Builder("red_nether_brick", BlockGetter.VERTICAL_SLAB, Blocks.RED_NETHER_BRICK_SLAB).build());
	public final Block QUARTZ_VERTICAL_SLAB = register(new Builder("quartz", BlockGetter.VERTICAL_SLAB, Blocks.QUARTZ_SLAB).build());
	public final Block SMOOTH_QUARTZ_VERTICAL_SLAB = register(new Builder("smooth_quartz", BlockGetter.VERTICAL_SLAB, Blocks.SMOOTH_QUARTZ_SLAB).build());
	public final Block END_STONE_BRICK_VERTICAL_SLAB = register(new Builder("end_stone_brick", BlockGetter.VERTICAL_SLAB, Blocks.END_STONE_BRICK_SLAB).build());
	public final Block PURPUR_VERTICAL_SLAB = register(new Builder("purpur", BlockGetter.VERTICAL_SLAB, Blocks.PURPUR_SLAB).build());
	public final Block PRISMARINE_VERTICAL_SLAB = register(new Builder("prismarine", BlockGetter.VERTICAL_SLAB, Blocks.PRISMARINE_SLAB).build());
	public final Block PRISMARINE_BRICK_VERTICAL_SLAB = register(new Builder("prismarine_brick", BlockGetter.VERTICAL_SLAB, Blocks.PRISMARINE_BRICK_SLAB).build());
	public final Block DARK_PRISMARINE_VERTICAL_SLAB = register(new Builder("dark_prismarine", BlockGetter.VERTICAL_SLAB, Blocks.DARK_PRISMARINE_SLAB).build());
	public final Block BLACKSTONE_VERTICAL_SLAB = register(new Builder("blackstone", BlockGetter.VERTICAL_SLAB, Blocks.BLACKSTONE_SLAB).build());
	public final Block POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = register(new Builder("polished_blackstone_brick", BlockGetter.VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB).build());
	public final Block POLISHED_BLACKSTONE_SLAB = register(new Builder("polished_blackstone", BlockGetter.VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_SLAB).build());

	public final Block DARK_PRISMARINE_WALL = register(new Builder("dark_prismarine", BlockGetter.WALL, Blocks.DARK_PRISMARINE).build());

	public final NormalWoodPack PALM_WOOD = register(new NormalWoodPack("palm", new OakSaplingGenerator(), MaterialColor.ORANGE, MaterialColor.CYAN_TERRACOTTA));
}
