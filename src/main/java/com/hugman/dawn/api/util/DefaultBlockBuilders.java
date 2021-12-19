package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.DoorBlock;
import com.hugman.dawn.api.object.block.MushroomPlantBlock;
import com.hugman.dawn.api.object.block.PressurePlateBlock;
import com.hugman.dawn.api.object.block.StairsBlock;
import com.hugman.dawn.api.object.block.StoneButtonBlock;
import com.hugman.dawn.api.object.block.TrapdoorBlock;
import com.hugman.dawn.api.object.block.WoodButtonBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.block.PressurePlateBlock.ActivationRule;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemGroup;

public class DefaultBlockBuilders {
	public static final BlockCreator.Builder CUBE = new BlockCreator.Builder().provider(Block::new).itemGroup(ItemGroup.BUILDING_BLOCKS);
	public static final BlockCreator.Builder PLANKS = new BlockCreator.Builder().provider(Block::new).itemGroup(ItemGroup.BUILDING_BLOCKS);
	public static final BlockCreator.Builder STAIRS = new BlockCreator.Builder().provider(StairsBlock::new).itemGroup(ItemGroup.BUILDING_BLOCKS);
	public static final BlockCreator.Builder SLAB = new BlockCreator.Builder().provider(SlabBlock::new).itemGroup(ItemGroup.BUILDING_BLOCKS);
	public static final BlockCreator.Builder WALL = new BlockCreator.Builder().provider(WallBlock::new).itemGroup(ItemGroup.DECORATIONS);
	public static final BlockCreator.Builder STONE_BUTTON = new BlockCreator.Builder().provider(s -> new StoneButtonBlock(s.noCollision().strength(0.5F))).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder WOOD_BUTTON = new BlockCreator.Builder().provider(s -> new WoodButtonBlock(s.noCollision().strength(0.5F))).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder STONE_PRESSURE_PLATE = new BlockCreator.Builder().provider(s -> new PressurePlateBlock(ActivationRule.MOBS, s.requiresTool().noCollision().strength(0.5F))).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder WOOD_PRESSURE_PLATE = new BlockCreator.Builder().provider(s -> new PressurePlateBlock(ActivationRule.EVERYTHING, s.noCollision().strength(0.5F))).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder TRAPDOOR = new BlockCreator.Builder().provider(s -> new TrapdoorBlock(s.strength(3.0F).nonOpaque().allowsSpawning(DefaultBlockSettings::never))).itemGroup(ItemGroup.REDSTONE).render(BlockCreator.Render.CUTOUT);
	public static final BlockCreator.Builder DOOR = new BlockCreator.Builder().provider(s -> new DoorBlock(s.strength(3.0F).nonOpaque())).itemGroup(ItemGroup.REDSTONE).render(BlockCreator.Render.CUTOUT);
	public static final BlockCreator.Builder FENCE = new BlockCreator.Builder().provider(FenceBlock::new).itemGroup(ItemGroup.DECORATIONS);
	public static final BlockCreator.Builder FENCE_GATE = new BlockCreator.Builder().provider(FenceGateBlock::new).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder LEAVES = new BlockCreator.Builder().provider(LeavesBlock::new).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT_MIPPED).flammability(30, 60).compostingChance(0.3f);
	public static final BlockCreator.Builder SAPLING = new BlockCreator.Builder().settings(DefaultBlockSettings.SAPLING).compostingChance(0.3f).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT_MIPPED);
	public static final BlockCreator.Builder MUSHROOM_BLOCK = new BlockCreator.Builder().provider(MushroomBlock::new).itemGroup(ItemGroup.DECORATIONS).compostingChance(0.85F);
	public static final BlockCreator.Builder MUSHROOM = new BlockCreator.Builder().provider(MushroomPlantBlock::new).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F);
}
