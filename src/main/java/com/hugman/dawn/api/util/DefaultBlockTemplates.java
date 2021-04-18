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

public class DefaultBlockTemplates {
	public static final BlockTemplate CUBE = new BlockTemplate(Block::new, "", ItemGroup.BUILDING_BLOCKS);
	public static final BlockTemplate PLANKS = new BlockTemplate(Block::new, "planks", ItemGroup.BUILDING_BLOCKS);
	public static final BlockTemplate STAIRS = new BlockTemplate(StairsBlock::new, "stairs", ItemGroup.BUILDING_BLOCKS);
	public static final BlockTemplate SLAB = new BlockTemplate(SlabBlock::new, "slab", ItemGroup.BUILDING_BLOCKS);
	public static final BlockTemplate WALL = new BlockTemplate(WallBlock::new, "wall", ItemGroup.DECORATIONS);
	public static final BlockTemplate STONE_BUTTON = new BlockTemplate(settings -> new StoneButtonBlock(settings.noCollision().strength(0.5F)), "button", ItemGroup.REDSTONE);
	public static final BlockTemplate WOOD_BUTTON = new BlockTemplate(settings -> new WoodButtonBlock(settings.noCollision().strength(0.5F)), "button", ItemGroup.REDSTONE);
	public static final BlockTemplate STONE_PRESSURE_PLATE = new BlockTemplate(settings -> new PressurePlateBlock(ActivationRule.MOBS, settings.requiresTool().noCollision().strength(0.5F)), "pressure_plate", ItemGroup.REDSTONE);
	public static final BlockTemplate WOOD_PRESSURE_PLATE = new BlockTemplate(settings -> new PressurePlateBlock(ActivationRule.EVERYTHING, settings.noCollision().strength(0.5F)), "pressure_plate", ItemGroup.REDSTONE);
	public static final BlockTemplate TRAPDOOR = new BlockTemplate(settings -> new TrapdoorBlock(settings.strength(3.0F).nonOpaque().allowsSpawning(DefaultBlockSettings::never)), "trapdoor", ItemGroup.REDSTONE, BlockCreator.Render.CUTOUT);
	public static final BlockTemplate DOOR = new BlockTemplate(settings -> new DoorBlock(settings.strength(3.0F).nonOpaque()), "door", ItemGroup.REDSTONE, BlockCreator.Render.CUTOUT);
	public static final BlockTemplate FENCE = new BlockTemplate(FenceBlock::new, "fence", ItemGroup.DECORATIONS);
	public static final BlockTemplate FENCE_GATE = new BlockTemplate(FenceGateBlock::new, "fence_gate", ItemGroup.REDSTONE);
	public static final BlockTemplate LEAVES = new BlockTemplate(LeavesBlock::new, "leaves", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED);
	public static final BlockTemplate MUSHROOM_BLOCK = new BlockTemplate(MushroomBlock::new, "mushroom_block", ItemGroup.DECORATIONS);
	public static final BlockTemplate MUSHROOM = new BlockTemplate(MushroomPlantBlock::new, "mushroom", ItemGroup.DECORATIONS);
}
