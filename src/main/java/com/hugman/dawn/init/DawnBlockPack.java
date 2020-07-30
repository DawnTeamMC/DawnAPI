package com.hugman.dawn.init;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.creator.pack.block.MCBlockPack;
import com.hugman.dawn.api.util.BlockSettings;
import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.DefaultBlockGetter;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class DawnBlockPack extends Pack {
	protected static <V, B extends CreatorBuilder> V register(B creatorBuilder) {
		return add(creatorBuilder, Dawn.MOD_DATA);
	}

	protected static <P extends Pack, B extends PackBuilder> P register(B packBuilder) {
		return add(packBuilder, Dawn.MOD_DATA);
	}

	public static final MCBlockPack CLOUD_BLOCKS = register(new MCBlockPack.Builder("", DefaultBlockGetter.FENCE_GATE, FabricBlockSettings.of(Material.LEAVES).sounds(BlockSoundGroup.WOOL).hardness(0f).noCollision()));

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
	public static final Block POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = register(new BlockCreator.Builder("polished_blackstone_brick", DefaultBlockGetter.VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB));

	public static final Block POLISHED_BLACKSTONE_SLAB = register(new BlockCreator.Builder("polished_blackstone", DefaultBlockGetter.VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_SLAB));
	public static final Block DARK_PRISMARINE_WALL = register(new BlockCreator.Builder("dark_prismarine", DefaultBlockGetter.WALL, Blocks.DARK_PRISMARINE));

	public static final Block OAK_LEAF_PILE = register(new BlockCreator.Builder("oak", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block SPRUCE_LEAF_PILE = register(new BlockCreator.Builder("spruce", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block BIRCH_LEAF_PILE = register(new BlockCreator.Builder("birch", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block JUNGLE_LEAF_PILE = register(new BlockCreator.Builder("jungle", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block ACACIA_LEAF_PILE = register(new BlockCreator.Builder("acacia", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
	public static final Block DARK_OAK_LEAF_PILE = register(new BlockCreator.Builder("dark_oak", DefaultBlockGetter.LEAF_PILE, BlockSettings.LEAF_PILE).copy(Blocks.OAK_LEAVES));
}
