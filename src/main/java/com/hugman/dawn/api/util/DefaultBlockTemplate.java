package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.DoorBlock;
import com.hugman.dawn.api.object.block.MushroomPlantBlock;
import com.hugman.dawn.api.object.block.PlantPileBlock;
import com.hugman.dawn.api.object.block.PressurePlateBlock;
import com.hugman.dawn.api.object.block.StairsBlock;
import com.hugman.dawn.api.object.block.StoneButtonBlock;
import com.hugman.dawn.api.object.block.TrapdoorBlock;
import com.hugman.dawn.api.object.block.WoodButtonBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.block.PressurePlateBlock.ActivationRule;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemGroup;

import java.util.function.Function;

public enum DefaultBlockTemplate implements BlockTemplate {
	CUBE(Block::new, "", ItemGroup.BUILDING_BLOCKS),
	PLANKS(Block::new, "planks", ItemGroup.BUILDING_BLOCKS),
	STAIRS(StairsBlock::new, "stairs", ItemGroup.BUILDING_BLOCKS),
	SLAB(SlabBlock::new, "slab", ItemGroup.BUILDING_BLOCKS),
	WALL(WallBlock::new, "wall", ItemGroup.DECORATIONS),
	STONE_BUTTON(settings -> new StoneButtonBlock(settings.noCollision().strength(0.5F)), "button", ItemGroup.REDSTONE),
	WOOD_BUTTON(settings -> new WoodButtonBlock(settings.noCollision().strength(0.5F)), "button", ItemGroup.REDSTONE),
	STONE_PRESSURE_PLATE(settings -> new PressurePlateBlock(ActivationRule.MOBS, settings.requiresTool().noCollision().strength(0.5F)), "pressure_plate", ItemGroup.REDSTONE),
	WOOD_PRESSURE_PLATE(settings -> new PressurePlateBlock(ActivationRule.EVERYTHING, settings.noCollision().strength(0.5F)), "pressure_plate", ItemGroup.REDSTONE),
	TRAPDOOR(settings -> new TrapdoorBlock(settings.strength(3.0F).nonOpaque().allowsSpawning(BlockSettings::never)), "trapdoor", ItemGroup.REDSTONE, BlockCreator.Render.CUTOUT),
	DOOR(settings -> new DoorBlock(settings.strength(3.0F).nonOpaque()), "door", ItemGroup.REDSTONE, BlockCreator.Render.CUTOUT),
	FENCE(FenceBlock::new, "fence", ItemGroup.DECORATIONS),
	FENCE_GATE(FenceGateBlock::new, "fence_gate", ItemGroup.REDSTONE),
	LEAVES(LeavesBlock::new, "leaves", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED),
	LEAF_PILE(PlantPileBlock::new, "leaf_pile", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED),
	PLANT_PILE(PlantPileBlock::new, "pile", ItemGroup.DECORATIONS, BlockCreator.Render.CUTOUT_MIPPED),
	MUSHROOM_BLOCK(MushroomBlock::new, "mushroom_block", ItemGroup.DECORATIONS),
	MUSHROOM(MushroomPlantBlock::new, "mushroom", ItemGroup.DECORATIONS);

	private final String suffix;
	private final ItemGroup itemGroup;
	private final BlockCreator.Render render;
	private final Function<AbstractBlock.Settings, ? extends Block> blockProvider;

	DefaultBlockTemplate(Function<AbstractBlock.Settings, ? extends Block> blockProvider, String suffix, ItemGroup itemGroup, BlockCreator.Render render) {
		this.suffix = suffix;
		this.itemGroup = itemGroup;
		this.render = render;
		this.blockProvider = blockProvider;
	}

	DefaultBlockTemplate(Function<AbstractBlock.Settings, ? extends Block> blockProvider, String suffix, ItemGroup itemGroup) {
		this(blockProvider, suffix, itemGroup, null);
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	@Override
	public ItemGroup getItemGroup() {
		return itemGroup;
	}

	@Override
	public BlockCreator.Render getRender() {
		return render;
	}

	@Override
	public Function<AbstractBlock.Settings, ? extends Block> getBlockProvider() {
		return blockProvider;
	}
}
