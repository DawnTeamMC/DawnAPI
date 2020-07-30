package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.DoorBlock;
import com.hugman.dawn.api.object.block.MushroomPlantBlock;
import com.hugman.dawn.api.object.block.PressurePlateBlock;
import com.hugman.dawn.api.object.block.StairsBlock;
import com.hugman.dawn.api.object.block.StoneButtonBlock;
import com.hugman.dawn.api.object.block.TrapdoorBlock;
import com.hugman.dawn.api.object.block.WoodButtonBlock;
import com.hugman.dawn.api.object.block.*;
import net.minecraft.block.*;
import net.minecraft.block.PressurePlateBlock.ActivationRule;
import net.minecraft.item.ItemGroup;

public enum DefaultBlockGetter implements BlockGetter {
	CUBE("", ItemGroup.BUILDING_BLOCKS),
	PLANKS("planks", ItemGroup.BUILDING_BLOCKS),
	STAIRS("stairs", ItemGroup.BUILDING_BLOCKS),
	SLAB("slab", ItemGroup.BUILDING_BLOCKS),
	VERTICAL_SLAB("vertical_slab", ItemGroup.BUILDING_BLOCKS),
	WALL("wall", ItemGroup.DECORATIONS),
	STONE_BUTTON("button", ItemGroup.REDSTONE),
	WOOD_BUTTON("button", ItemGroup.REDSTONE),
	STONE_PRESSURE_PLATE("pressure_plate", ItemGroup.REDSTONE),
	WOOD_PRESSURE_PLATE("pressure_plate", ItemGroup.REDSTONE),
	TRAPDOOR("trapdoor", ItemGroup.REDSTONE, BlockCreator.Render.CUTOUT),
	DOOR("door", ItemGroup.REDSTONE, BlockCreator.Render.CUTOUT),
	FENCE("fence", ItemGroup.DECORATIONS),
	FENCE_GATE("fence_gate", ItemGroup.REDSTONE),
	LEAVES("leaves", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED),
	LEAF_PILE("leaf_pile", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED),
	PLANT_PILE("pile", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED),
	MUSHROOM_BLOCK("mushroom_block", ItemGroup.DECORATIONS),
	MUSHROOM("mushroom", ItemGroup.DECORATIONS);

	private final String suffix;
	private final ItemGroup itemGroup;
	private final BlockCreator.Render render;

	DefaultBlockGetter(String suffix, ItemGroup itemGroup, BlockCreator.Render render) {
		this.suffix = suffix;
		this.itemGroup = itemGroup;
		this.render = render;
	}

	DefaultBlockGetter(String suffix, ItemGroup itemGroup) {
		this(suffix, itemGroup, null);
	}

	public String getSuffix() {
		if(suffix.isEmpty()) {
			return suffix;
		}
		else {
			return "_" + suffix;
		}
	}

	public ItemGroup getItemGroup() {
		return itemGroup;
	}

	public BlockCreator.Render getRender() {
		return render;
	}

	public Block getBlock(AbstractBlock.Settings settings) {
		switch(this) {
			case CUBE:
			default:
				return new Block(settings);
			case SLAB:
				return new SlabBlock(settings);
			case STAIRS:
				return new StairsBlock(settings);
			case VERTICAL_SLAB:
				return new VerticalSlabBlock(settings);
			case WALL:
				return new WallBlock(settings);
			case STONE_PRESSURE_PLATE:
				return new PressurePlateBlock(ActivationRule.MOBS, settings);
			case WOOD_PRESSURE_PLATE:
				return new PressurePlateBlock(ActivationRule.EVERYTHING, settings);
			case STONE_BUTTON:
				return new StoneButtonBlock(settings);
			case WOOD_BUTTON:
				return new WoodButtonBlock(settings);
			case TRAPDOOR:
				return new TrapdoorBlock(settings);
			case DOOR:
				return new DoorBlock(settings);
			case FENCE:
				return new FenceBlock(settings);
			case FENCE_GATE:
				return new FenceGateBlock(settings);
			case LEAVES:
				return new LeavesBlock(settings);
			case LEAF_PILE:
			case PLANT_PILE:
				return new PlantPileBlock(settings);
			case MUSHROOM:
				return new MushroomPlantBlock(settings);
			case MUSHROOM_BLOCK:
				return new MushroomBlock(settings);
		}
	}
}
