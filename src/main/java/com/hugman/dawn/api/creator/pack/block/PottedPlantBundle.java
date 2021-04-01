package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.Bundle;
import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.util.BlockSettings;
import com.hugman.dawn.api.util.ModData;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;

import java.util.ArrayList;
import java.util.List;

public class PottedPlantBundle extends Bundle {
	private final BlockCreator plant;
	private final BlockCreator pottedPlant;

	public PottedPlantBundle(BlockCreator.Builder builder) {
		this.plant = put(builder.build());
		this.pottedPlant = put(builder.id("potted_" + builder.getId()).block(settings -> new FlowerPotBlock(getPlant(), settings)).settings(BlockSettings.POTTED_PLANT.lightLevel(getPlant().getDefaultState().getLuminance())).render(BlockCreator.Render.CUTOUT).noItem().build());
	}

	public Block getPlant() {
		return this.plant.getBlock();
	}

	public Block getPottedPlant() {
		return this.pottedPlant.getBlock();
	}

	@Override
	public List<Creator> open() {
		List<Creator> list = new ArrayList<>();
		list.add(plant);
		list.add(pottedPlant);
		return list;
	}
}