package com.hugman.dawn.util.creator.pack;

import com.hugman.dawn.util.creator.BlockCreator;
import com.hugman.dawn.util.creator.BlockCreator.Builder;
import com.hugman.dawn.util.creator.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;

public class PottedPlantEntryPack extends Pack<Block>{
	private final Block plant;
	private final Block pottedPlant;

	public PottedPlantEntryPack(BlockCreator entry) {
		this.plant = add(entry);
		this.pottedPlant = add(new Builder("potted_" + entry.getName(), new FlowerPotBlock(getPlant(), BlockSettings.POTTED_PLANT.lightLevel(plant.getDefaultState().getLuminance()))).setRender(BlockCreator.Render.CUTOUT).noItem().build());
	}

	public Block getPlant() {
		return this.plant;
	}

	public Block getPottedPlant() {
		return this.pottedPlant;
	}
}