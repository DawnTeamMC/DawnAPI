package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.Bundle;
import com.hugman.dawn.api.util.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;

public class PottedPlantBundle extends Bundle {
	private final BlockCreator plant;
	private final BlockCreator pottedPlant;

	public PottedPlantBundle(BlockCreator.Builder builder) {
		this.plant = put(builder.build());
		this.pottedPlant = put(builder.id("potted_" + builder.getId()).block(settings -> new FlowerPotBlock(plant.getBlock(), settings)).settings(BlockSettings.POTTED_PLANT.luminance(getPlant().getDefaultState().getLuminance())).render(BlockCreator.Render.CUTOUT).noItem().build());
	}

	public Block getPlant() {
		return this.plant.getBlock();
	}

	public Block getPottedPlant() {
		return this.pottedPlant.getBlock();
	}
}