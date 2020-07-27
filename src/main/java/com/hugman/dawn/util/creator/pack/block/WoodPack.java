package com.hugman.dawn.util.creator.pack.block;

import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockGetter;
import com.hugman.dawn.util.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;

public class WoodPack extends Pack.Builder<Block> {
	protected final String suffix;
	protected final MaterialColor planksColor;
	protected final MaterialColor insideColor;
	protected final MaterialColor barkColor;
	protected final boolean isNether;

	private BlockCreator planks;
	private LogsPack logsPack;
	private MSBlockPack woodenBlocksPack;
	private MSBlockPack barkBlocksPack;

	public WoodPack(String suffix, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor, boolean isNether) {
		this.suffix = suffix;
		this.planksColor = planksColor;
		this.insideColor = insideColor;
		this.barkColor = barkColor;
		this.isNether = isNether;
	}

	@Override
	public Pack<Block> build() {
		FabricBlockSettings settings = FabricBlockSettings.copyOf(isNether ? Blocks.CRIMSON_PLANKS : Blocks.OAK_PLANKS).materialColor(planksColor);
		this.planks = add(new BlockCreator.Builder(suffix, BlockGetter.PLANKS, settings));
		this.logsPack = (LogsPack) add(new LogsPack(suffix, insideColor, barkColor, isNether));
		this.woodenBlocksPack = (MSBlockPack) add(new MSBlockPack(suffix, settings, this.getPlanks(),
				BlockGetter.STAIRS,
				BlockGetter.SLAB,
				BlockGetter.VERTICAL_SLAB,
				BlockGetter.TRAPDOOR,
				BlockGetter.WOOD_PRESSURE_PLATE,
				BlockGetter.WOOD_BUTTON,
				BlockGetter.FENCE,
				BlockGetter.FENCE_GATE,
				BlockGetter.DOOR));
		this.barkBlocksPack = (MSBlockPack) add(new MSBlockPack(suffix + logsPack.getWoodSuffix(), settings.materialColor(barkColor), logsPack.getWood(),
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
