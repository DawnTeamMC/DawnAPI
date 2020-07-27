package com.hugman.dawn.util.pack.block;

import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;

public class WoodPack extends Pack.Builder<Block> {
	private final String name;
	private final MaterialColor planksColor;
	private final MaterialColor insideColor;
	private final MaterialColor barkColor;
	private final boolean isNether;

	private BlockCreator planks;
	private Pack<Block> logsPack;
	private MSBlockPack woodenBlocksPack;
	private MSBlockPack barkBlocksPack;

	public WoodPack(String name, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor, boolean isNether) {
		this.name = name;
		this.planksColor = planksColor;
		this.insideColor = insideColor;
		this.barkColor = barkColor;
		this.isNether = isNether;
	}

	@Override
	public Pack<Block> build() {
		FabricBlockSettings settings = FabricBlockSettings.copyOf(isNether ? Blocks.CRIMSON_PLANKS : Blocks.OAK_PLANKS).materialColor(planksColor);
		this.planks = add(new BlockCreator.Builder(name, BlockGetter.PLANKS, settings));
		this.logsPack = add(new LogsPack(name, insideColor, barkColor, isNether));
		this.woodenBlocksPack = add(new MSBlockPack(name, settings, this.getPlanks(),
				BlockGetter.STAIRS,
				BlockGetter.SLAB,
				BlockGetter.VERTICAL_SLAB,
				BlockGetter.TRAPDOOR,
				BlockGetter.WOOD_PRESSURE_PLATE,
				BlockGetter.WOOD_BUTTON,
				BlockGetter.FENCE,
				BlockGetter.FENCE_GATE,
				BlockGetter.DOOR));
		this.barkBlocksPack = add(new MSBlockPack(name + logsPack.getWoodSuffix(), settings.materialColor(barkColor), logsPack.getWood(),
				BlockGetter.STAIRS,
				BlockGetter.SLAB,
				BlockGetter.VERTICAL_SLAB,
				BlockGetter.WOOD_BUTTON));
		return super.build();
	}

	public Block getPlanks() {
		return planks.getValue();
	}

	public Block getLog() {
		return logsPack.getLog();
	}

	public Block getStrippedLog() {
		return logsPack.getStrippedLog();
	}

	public Block getWood() {
		return logsPack.getWood();
	}

	public Block getStrippedWood() {
		return logsPack.getStrippedWood();
	}

	public Block getWoodStairs() {
		return barkBlocksPack.getBlock(BlockGetter.STAIRS);
	}

	public Block getWoodSlab() {
		return barkBlocksPack.getBlock(BlockGetter.SLAB);
	}

	public Block getWoodVerticalSlab() {
		return barkBlocksPack.getBlock(BlockGetter.VERTICAL_SLAB);
	}

	public Block getWoodButton() {
		return barkBlocksPack.getBlock(BlockGetter.WOOD_BUTTON);
	}

	public Block getPressurePlate() {
		return woodenBlocksPack.getBlock(BlockGetter.WOOD_PRESSURE_PLATE);
	}

	public Block getTrapdoor() {
		return woodenBlocksPack.getBlock(BlockGetter.TRAPDOOR);
	}

	public Block getButton() {
		return woodenBlocksPack.getBlock(BlockGetter.WOOD_BUTTON);
	}

	public Block getStairs() {
		return woodenBlocksPack.getBlock(BlockGetter.STAIRS);
	}

	public Block getSlab() {
		return woodenBlocksPack.getBlock(BlockGetter.SLAB);
	}

	public Block getVerticalSlab() {
		return woodenBlocksPack.getBlock(BlockGetter.VERTICAL_SLAB);
	}

	public Block getFenceGate() {
		return woodenBlocksPack.getBlock(BlockGetter.FENCE_GATE);
	}

	public Block getFence() {
		return woodenBlocksPack.getBlock(BlockGetter.FENCE);
	}

	public Block getDoor() {
		return woodenBlocksPack.getBlock(BlockGetter.DOOR);
	}
}
