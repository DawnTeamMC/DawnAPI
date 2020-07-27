package com.hugman.dawn.util.creator;

import com.hugman.dawn.object.block.DoorBlock;
import com.hugman.dawn.object.block.MushroomPlantBlock;
import com.hugman.dawn.object.block.PressurePlateBlock;
import com.hugman.dawn.object.block.StairsBlock;
import com.hugman.dawn.object.block.StoneButtonBlock;
import com.hugman.dawn.object.block.TrapdoorBlock;
import com.hugman.dawn.object.block.WoodButtonBlock;
import com.hugman.dawn.object.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.PressurePlateBlock.ActivationRule;

public enum BlockGetter {
	CUBE,
	STAIRS,
	SLAB,
	VERTICAL_SLAB,
	WALL,
	STONE_BUTTON,
	WOOD_BUTTON,
	STONE_PRESSURE_PLATE,
	WOOD_PRESSURE_PLATE,
	TRAPDOOR,
	DOOR,
	FENCE,
	FENCE_GATE,
	LEAVES,
	PLANT_PILE,
	MUSHROOM_BLOCK,
	MUSHROOM;

	public Block getBlock(FabricBlockSettings settings) {
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
				return new PressurePlateBlock(ActivationRule.MOBS, settings.noCollision().strength(0.5F));
			case WOOD_PRESSURE_PLATE:
				return new PressurePlateBlock(ActivationRule.EVERYTHING, settings.noCollision().strength(0.5F, 0.0F));
			case STONE_BUTTON:
				return new StoneButtonBlock(settings.noCollision().hardness(0.5F).materialColor(MaterialColor.CLEAR));
			case WOOD_BUTTON:
				return new WoodButtonBlock(settings.noCollision().hardness(0.5F).materialColor(MaterialColor.CLEAR));
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
			case PLANT_PILE:
				return new PlantPileBlock(settings);
			case MUSHROOM:
				return new MushroomPlantBlock(settings);
			case MUSHROOM_BLOCK:
				return new MushroomBlock(settings);
		}
	}
}
