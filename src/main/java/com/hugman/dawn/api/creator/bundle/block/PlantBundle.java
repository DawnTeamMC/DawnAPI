package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;

public class PlantBundle extends Bundle
{
	private final BlockCreator plant;
	private final BlockCreator pottedPlant;

	/**
	 * Bundle builder that contains the plant block with its potted form.
	 *
	 * @param builder the plant builder
	 */
	public PlantBundle(BlockCreator.Builder builder) {
		this.plant = put(builder.build());
		this.pottedPlant = put(builder.copy("potted_" + builder.getName()).provider(settings -> new FlowerPotBlock(plant.getValue(), settings)).settings(DefaultBlockSettings.POTTED_PLANT.luminance(getPlant().getDefaultState().getLuminance())).render(BlockCreator.Render.CUTOUT).noItem().build());
	}

	public Block getPlant() {
		return this.plant.getValue();
	}

	public Block getPottedPlant() {
		return this.pottedPlant.getValue();
	}
}