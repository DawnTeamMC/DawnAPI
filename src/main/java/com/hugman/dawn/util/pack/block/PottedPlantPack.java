package com.hugman.dawn.util.pack.block;

import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockCreator.Builder;
import com.hugman.dawn.util.creator.block.BlockSettings;
import com.hugman.dawn.util.pack.Pack;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;

public class PottedPlantPack extends Pack.Builder<Block> {
	private final Builder plantBuilder;

	private BlockCreator plant;
	private BlockCreator pottedPlant;

	/**
	 * Creates a creator pack containing a plant and its potted variant.
	 *
	 * @param builder The plant builder.
	 */
	public PottedPlantPack(BlockCreator.Builder builder) {
		this.plantBuilder = builder;
	}

	@Override
	public Pack<Block> build() {
		this.plant = add(plantBuilder);
		this.pottedPlant = add(new Builder("potted_" + plant.getName(), new FlowerPotBlock(getPlant(), BlockSettings.POTTED_PLANT.lightLevel(getPlant().getDefaultState().getLuminance()))).setRender(BlockCreator.Render.CUTOUT).noItem());
		return super.build();
	}

	public Block getPlant() {
		return this.plant.getValue();
	}

	public Block getPottedPlant() {
		return this.pottedPlant.getValue();
	}
}