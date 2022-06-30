package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.DoorBlock;
import com.hugman.dawn.api.object.block.MushroomPlantBlock;
import com.hugman.dawn.api.object.block.PressurePlateBlock;
import com.hugman.dawn.api.object.block.StairsBlock;
import com.hugman.dawn.api.object.block.StoneButtonBlock;
import com.hugman.dawn.api.object.block.TrapdoorBlock;
import com.hugman.dawn.api.object.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.PressurePlateBlock.ActivationRule;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;

public final class DefaultBlockBuilders {
	public static final BlockCreator.Builder CUBE = new BlockCreator.Builder().provider(Block::new).itemGroup(ItemGroup.BUILDING_BLOCKS);
	public static final BlockCreator.Builder PLANKS = new BlockCreator.Builder().provider(Block::new).itemGroup(ItemGroup.BUILDING_BLOCKS);
	public static final BlockCreator.Builder STAIRS = new BlockCreator.Builder().provider(StairsBlock::new).itemGroup(ItemGroup.BUILDING_BLOCKS);
	public static final BlockCreator.Builder SLAB = new BlockCreator.Builder().provider(SlabBlock::new).itemGroup(ItemGroup.BUILDING_BLOCKS);
	public static final BlockCreator.Builder WALL = new BlockCreator.Builder().provider(WallBlock::new).itemGroup(ItemGroup.DECORATIONS);
	public static final BlockCreator.Builder STONE_BUTTON = new BlockCreator.Builder().provider(s -> new StoneButtonBlock(s.noCollision().strength(0.5F))).settings(FabricBlockSettings.of(Material.DECORATION).noCollision().hardness(0.5F)).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder WOOD_BUTTON = new BlockCreator.Builder().provider(s -> new WoodButtonBlock(s.noCollision().strength(0.5F))).settings(FabricBlockSettings.of(Material.DECORATION).noCollision().hardness(0.5F).sounds(BlockSoundGroup.WOOD)).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder STONE_PRESSURE_PLATE = new BlockCreator.Builder().provider(s -> new PressurePlateBlock(ActivationRule.MOBS, s.requiresTool().noCollision().strength(0.5F))).settings(FabricBlockSettings.of(Material.STONE).requiresTool().noCollision().strength(0.5F)).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder WOOD_PRESSURE_PLATE = new BlockCreator.Builder().provider(s -> new PressurePlateBlock(ActivationRule.EVERYTHING, s.noCollision().strength(0.5F))).settings(FabricBlockSettings.of(Material.WOOD).noCollision().strength(0.5F).sounds(BlockSoundGroup.WOOD)).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder TRAPDOOR = new BlockCreator.Builder().provider(s -> new TrapdoorBlock(s.strength(3.0F).nonOpaque().allowsSpawning(DefaultBlockSettings::never))).itemGroup(ItemGroup.REDSTONE).render(BlockCreator.Render.CUTOUT);
	public static final BlockCreator.Builder DOOR = new BlockCreator.Builder().provider(s -> new DoorBlock(s.strength(3.0F).nonOpaque())).itemGroup(ItemGroup.REDSTONE).render(BlockCreator.Render.CUTOUT);
	public static final BlockCreator.Builder FENCE = new BlockCreator.Builder().provider(FenceBlock::new).itemGroup(ItemGroup.DECORATIONS);
	public static final BlockCreator.Builder FENCE_GATE = new BlockCreator.Builder().provider(FenceGateBlock::new).itemGroup(ItemGroup.REDSTONE);
	public static final BlockCreator.Builder LEAVES = new BlockCreator.Builder().settings(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(DefaultBlockSettings::canSpawnOnLeaves).suffocates(DefaultBlockSettings::never).blockVision(DefaultBlockSettings::never)).provider(LeavesBlock::new).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT_MIPPED).flammability(30, 60).compostingChance(0.3f);
	public static final BlockCreator.Builder SAPLING = new BlockCreator.Builder().settings(FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.GRASS).breakInstantly().noCollision().ticksRandomly()).compostingChance(0.3f).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT_MIPPED);
	public static final BlockCreator.Builder MUSHROOM_BLOCK = new BlockCreator.Builder().provider(MushroomBlock::new).settings(FabricBlockSettings.of(Material.WOOD).hardness(0.2F).sounds(BlockSoundGroup.WOOD)).itemGroup(ItemGroup.DECORATIONS).compostingChance(0.85F);
	public static final BlockCreator.Builder MUSHROOM = new BlockCreator.Builder().provider(MushroomPlantBlock::new).settings(FabricBlockSettings.of(Material.PLANT).noCollision().hardness(0.0F).sounds(BlockSoundGroup.GRASS).luminance(1)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT).compostingChance(0.65F);
}
