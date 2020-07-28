package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockGetter;
import com.hugman.dawn.api.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MaterialColor;

public class WoodPack extends Pack<Block> {
	private final BlockCreator planks;
	private final LogsPack logsPack;
	private final MSBlockPack woodenBlocksPack;
	private final MSBlockPack barkBlocksPack;

	/**
	 * Creates an entry pack containing blocks for basic wood types.
	 *
	 * @param suffix      The suffix of the wood type. (ex: <code>oak</code>)
	 * @param planksColor The material color of the planks.
	 * @param insideColor The material color of the inside of logs.
	 * @param barkColor   The material color of the bark side of logs.
	 * @param isNether    Defines if the wood type comes from the nether. (changes the name, sounds and materials)
	 */
	public WoodPack(String suffix, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor, boolean isNether) {
		FabricBlockSettings settings = FabricBlockSettings.copyOf(isNether ? Blocks.CRIMSON_PLANKS : Blocks.OAK_PLANKS).materialColor(planksColor);
		this.planks = add(new BlockCreator.Builder(suffix, BlockGetter.PLANKS, settings));
		this.logsPack = add(new LogsPack(suffix, insideColor, barkColor, isNether));
		this.woodenBlocksPack = add(new MSBlockPack(suffix, settings, this.getPlanks(),
				BlockGetter.STAIRS,
				BlockGetter.SLAB,
				BlockGetter.VERTICAL_SLAB,
				BlockGetter.TRAPDOOR,
				BlockGetter.WOOD_PRESSURE_PLATE,
				BlockGetter.WOOD_BUTTON,
				BlockGetter.FENCE,
				BlockGetter.FENCE_GATE,
				BlockGetter.DOOR));
		this.barkBlocksPack = add(new MSBlockPack(suffix + logsPack.getWoodSuffix(), settings.materialColor(barkColor), logsPack.getWood(),
				BlockGetter.STAIRS,
				BlockGetter.SLAB,
				BlockGetter.VERTICAL_SLAB,
				BlockGetter.WOOD_BUTTON));
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
