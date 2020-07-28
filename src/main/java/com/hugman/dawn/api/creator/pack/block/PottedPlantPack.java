package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockCreator.Builder;
import com.hugman.dawn.api.creator.BlockSettings;
import com.hugman.dawn.api.creator.pack.Pack;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;

public class PottedPlantPack extends Pack<Block> {
	private BlockCreator plant;
	private BlockCreator pottedPlant;

	/**
	 * Creates a creator pack containing a plant and its potted variant.
	 *
	 * @param builder The plant builder.
	 */
	public PottedPlantPack(BlockCreator.Builder builder) {
		this.plant = add(builder);
		this.pottedPlant = add(new Builder("potted_" + plant.getName(), new FlowerPotBlock(getPlant(), BlockSettings.POTTED_PLANT.lightLevel(getPlant().getDefaultState().getLuminance()))).setRender(BlockCreator.Render.CUTOUT).noItem());
	}

	public Block getPlant() {
		return this.plant.getValue();
	}

	public Block getPottedPlant() {
		return this.pottedPlant.getValue();
	}
}