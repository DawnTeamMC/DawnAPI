package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;

public class PlantBundle extends Bundle {
	private final BlockCreator plant;
	private final BlockCreator pottedPlant;

	public PlantBundle(BlockCreator.Builder builder) {
		this.plant = put(builder.build());
		this.pottedPlant = put(builder.name("potted_" + builder.getName()).blockProvider(settings -> new FlowerPotBlock(plant.getValue(), settings)).settings(BlockSettings.POTTED_PLANT.luminance(getPlant().getDefaultState().getLuminance())).render(BlockCreator.Render.CUTOUT).noItem().build());
	}

	public Block getPlant() {
		return this.plant.getValue();
	}

	public Block getPottedPlant() {
		return this.pottedPlant.getValue();
	}
}