package com.hugman.dawn.init;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.creator.pack.block.PottedPlantPack;
import com.hugman.dawn.api.object.block.FertilizableMushroomPlantBlock;
import com.hugman.dawn.api.util.BlockSettings;
import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.DefaultBlockGetter;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;

public class DawnBlockPack extends Pack {
	protected static <V, B extends CreatorBuilder> V register(B creatorBuilder) {
		return add(creatorBuilder, Dawn.MOD_DATA);
	}

	protected static <P extends Pack, B extends PackBuilder> P register(B packBuilder) {
		return add(packBuilder, Dawn.MOD_DATA);
	}

	public static final Block OAK_VERTICAL_SLAB = register(new BlockCreator.Builder("oak", DefaultBlockGetter.VERTICAL_SLAB, Blocks.OAK_SLAB));
	public static final Block SPRUCE_VERTICAL_SLAB = register(new BlockCreator.Builder("spruce", DefaultBlockGetter.VERTICAL_SLAB, Blocks.SPRUCE_SLAB));
	public static final Block BIRCH_VERTICAL_SLAB = register(new BlockCreator.Builder("birch", DefaultBlockGetter.VERTICAL_SLAB, Blocks.BIRCH_SLAB));
	public static final Block JUNGLE_VERTICAL_SLAB = register(new BlockCreator.Builder("jungle", DefaultBlockGetter.VERTICAL_SLAB, Blocks.JUNGLE_SLAB));
	public static final Block ACACIA_VERTICAL_SLAB = register(new BlockCreator.Builder("acacia", DefaultBlockGetter.VERTICAL_SLAB, Blocks.ACACIA_SLAB));
	public static final Block DARK_OAK_VERTICAL_SLAB = register(new BlockCreator.Builder("dark_oak", DefaultBlockGetter.VERTICAL_SLAB, Blocks.DARK_OAK_SLAB));
	public static final Block CRIMSON_VERTICAL_SLAB = register(new BlockCreator.Builder("crimson", DefaultBlockGetter.VERTICAL_SLAB, Blocks.CRIMSON_SLAB));
	public static final Block WARPED_VERTICAL_SLAB = register(new BlockCreator.Builder("warped", DefaultBlockGetter.VERTICAL_SLAB, Blocks.WARPED_SLAB));
	public static final Block STONE_VERTICAL_SLAB = register(new BlockCreator.Builder("stone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.STONE_SLAB));
	public static final Block SMOOTH_STONE_VERTICAL_SLAB = register(new BlockCreator.Builder("smooth_stone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.SMOOTH_STONE_SLAB));
	public static final Block COBBLESTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("cobblestone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.COBBLESTONE_SLAB));
	public static final Block MOSSY_COBBLESTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("mossy_cobblestone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB));
	public static final Block STONE_BRICK_VERTICAL_SLAB = register(new BlockCreator.Builder("stone_brick", DefaultBlockGetter.VERTICAL_SLAB, Blocks.STONE_BRICK_SLAB));
	public static final Block MOSSY_STONE_BRICK_VERTICAL_SLAB = register(new BlockCreator.Builder("mossy_stone_brick", DefaultBlockGetter.VERTICAL_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB));
	public static final Block GRANITE_VERTICAL_SLAB = register(new BlockCreator.Builder("granite", DefaultBlockGetter.VERTICAL_SLAB, Blocks.GRANITE_SLAB));
	public static final Block POLISHED_GRANITE_VERTICAL_SLAB = register(new BlockCreator.Builder("polished_granite", DefaultBlockGetter.VERTICAL_SLAB, Blocks.POLISHED_GRANITE_SLAB));
	public static final Block DIORITE_VERTICAL_SLAB = register(new BlockCreator.Builder("diorite", DefaultBlockGetter.VERTICAL_SLAB, Blocks.DIORITE_SLAB));
	public static final Block POLISHED_DIORITE_VERTICAL_SLAB = register(new BlockCreator.Builder("polished_diorite", DefaultBlockGetter.VERTICAL_SLAB, Blocks.POLISHED_DIORITE_SLAB));
	public static final Block ANDESITE_VERTICAL_SLAB = register(new BlockCreator.Builder("andesite", DefaultBlockGetter.VERTICAL_SLAB, Blocks.ANDESITE_SLAB));
	public static final Block POLISHED_ANDESITE_VERTICAL_SLAB = register(new BlockCreator.Builder("polished_andesite", DefaultBlockGetter.VERTICAL_SLAB, Blocks.POLISHED_ANDESITE_SLAB));
	public static final Block BRICK_VERTICAL_SLAB = register(new BlockCreator.Builder("brick", DefaultBlockGetter.VERTICAL_SLAB, Blocks.BRICK_SLAB));
	public static final Block SANDSTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("sandstone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.SANDSTONE_SLAB));
	public static final Block CUT_SANDSTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("cut_sandstone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.CUT_SANDSTONE_SLAB));
	public static final Block SMOOTH_SANDSTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("smooth_sandstone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB));
	public static final Block RED_SANDSTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("red_sandstone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.RED_SANDSTONE_SLAB));
	public static final Block CUT_RED_SANDSTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("cut_red_sandstone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.CUT_RED_SANDSTONE_SLAB));
	public static final Block SMOOTH_RED_SANDSTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("smooth_red_sandstone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB));
	public static final Block NETHER_BRICK_VERTICAL_SLAB = register(new BlockCreator.Builder("nether_brick", DefaultBlockGetter.VERTICAL_SLAB, Blocks.NETHER_BRICK_SLAB));
	public static final Block RED_NETHER_BRICK_VERTICAL_SLAB = register(new BlockCreator.Builder("red_nether_brick", DefaultBlockGetter.VERTICAL_SLAB, Blocks.RED_NETHER_BRICK_SLAB));
	public static final Block QUARTZ_VERTICAL_SLAB = register(new BlockCreator.Builder("quartz", DefaultBlockGetter.VERTICAL_SLAB, Blocks.QUARTZ_SLAB));
	public static final Block SMOOTH_QUARTZ_VERTICAL_SLAB = register(new BlockCreator.Builder("smooth_quartz", DefaultBlockGetter.VERTICAL_SLAB, Blocks.SMOOTH_QUARTZ_SLAB));
	public static final Block END_STONE_BRICK_VERTICAL_SLAB = register(new BlockCreator.Builder("end_stone_brick", DefaultBlockGetter.VERTICAL_SLAB, Blocks.END_STONE_BRICK_SLAB));
	public static final Block PURPUR_VERTICAL_SLAB = register(new BlockCreator.Builder("purpur", DefaultBlockGetter.VERTICAL_SLAB, Blocks.PURPUR_SLAB));
	public static final Block PRISMARINE_VERTICAL_SLAB = register(new BlockCreator.Builder("prismarine", DefaultBlockGetter.VERTICAL_SLAB, Blocks.PRISMARINE_SLAB));
	public static final Block PRISMARINE_BRICK_VERTICAL_SLAB = register(new BlockCreator.Builder("prismarine_brick", DefaultBlockGetter.VERTICAL_SLAB, Blocks.PRISMARINE_BRICK_SLAB));
	public static final Block DARK_PRISMARINE_VERTICAL_SLAB = register(new BlockCreator.Builder("dark_prismarine", DefaultBlockGetter.VERTICAL_SLAB, Blocks.DARK_PRISMARINE_SLAB));
	public static final Block BLACKSTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("blackstone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.BLACKSTONE_SLAB));
	public static final Block POLISHED_BLACKSTONE_VERTICAL_SLAB = register(new BlockCreator.Builder("polished_blackstone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_SLAB));
	public static final Block POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = register(new BlockCreator.Builder("polished_blackstone_brick", DefaultBlockGetter.VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB));

	public static final Block OAK_LEAF_PILE = register(new BlockCreator.Builder("oak", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block SPRUCE_LEAF_PILE = register(new BlockCreator.Builder("spruce", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block BIRCH_LEAF_PILE = register(new BlockCreator.Builder("birch", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block JUNGLE_LEAF_PILE = register(new BlockCreator.Builder("jungle", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block ACACIA_LEAF_PILE = register(new BlockCreator.Builder("acacia", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block DARK_OAK_LEAF_PILE = register(new BlockCreator.Builder("dark_oak", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));

	public static final Block WHITE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("white", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.WHITE)));
	public static final Block LIGHT_GRAY_MUSHROOM_BLOCK = register(new BlockCreator.Builder("light_gray", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.LIGHT_GRAY)));
	public static final Block GRAY_MUSHROOM_BLOCK = register(new BlockCreator.Builder("gray", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.GRAY)));
	public static final Block BLACK_MUSHROOM_BLOCK = register(new BlockCreator.Builder("black", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.BLACK)));
	public static final Block ORANGE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("orange", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.ORANGE)));
	public static final Block YELLOW_MUSHROOM_BLOCK = register(new BlockCreator.Builder("yellow", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.YELLOW)));
	public static final Block LIME_MUSHROOM_BLOCK = register(new BlockCreator.Builder("lime", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.LIME)));
	public static final Block GREEN_MUSHROOM_BLOCK = register(new BlockCreator.Builder("green", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.GREEN)));
	public static final Block CYAN_MUSHROOM_BLOCK = register(new BlockCreator.Builder("cyan", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.CYAN)));
	public static final Block LIGHT_BLUE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("light_blue", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.LIGHT_BLUE)));
	public static final Block BLUE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("blue", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.BLUE)));
	public static final Block PURPLE_MUSHROOM_BLOCK = register(new BlockCreator.Builder("purple", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.PURPLE)));
	public static final Block MAGENTA_MUSHROOM_BLOCK = register(new BlockCreator.Builder("magenta", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.MAGENTA)));
	public static final Block PINK_MUSHROOM_BLOCK = register(new BlockCreator.Builder("pink", DefaultBlockGetter.MUSHROOM_BLOCK, BlockSettings.MUSHROOM_BLOCK.materialColor(DyeColor.PINK)));
	public static final PottedPlantPack WHITE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("white_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, WHITE_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack LIGHT_GRAY_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("light_gray_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, LIGHT_GRAY_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack GRAY_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("gray_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, GRAY_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack BLACK_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("black_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, BLACK_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack ORANGE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("orange_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, ORANGE_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack YELLOW_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("yellow_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, YELLOW_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack LIME_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("lime_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, LIME_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack GREEN_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("green_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, GREEN_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack CYAN_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("cyan_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, CYAN_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack LIGHT_BLUE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("light_blue_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, LIGHT_BLUE_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack BLUE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("blue_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, BLUE_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack PURPLE_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("purple_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, PURPLE_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack MAGENTA_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("magenta_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, MAGENTA_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
	public static final PottedPlantPack PINK_MUSHROOM = register(new PottedPlantPack.Builder(new BlockCreator.Builder("pink_mushroom", new FertilizableMushroomPlantBlock(BlockSettings.MUSHROOM, PINK_MUSHROOM_BLOCK)).setItemGroup(ItemGroup.DECORATIONS).setRender(BlockCreator.Render.CUTOUT)));
}
