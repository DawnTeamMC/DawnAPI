package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockSettings;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.util.registry.Registry;

public class PottedPlantPack extends Pack {
	private final Block plant;
	private final Block pottedPlant;

	protected PottedPlantPack(ModData modData, BlockCreator.Builder builder) {
		this.plant = add(builder, modData);
		this.pottedPlant = add(new BlockCreator.Builder("potted_" + Registry.BLOCK.getId(plant).getPath(), new FlowerPotBlock(getPlant(), BlockSettings.POTTED_PLANT.lightLevel(getPlant().getDefaultState().getLuminance()))).setRender(BlockCreator.Render.CUTOUT).noItem(), modData);
	}

	public static class Builder extends Pack.Builder {
		private final BlockCreator.Builder builder;

		/**
		 * Creates a creator pack containing a plant and its potted variant.
		 *
		 * @param builder The plant builder.
		 */
		public Builder(BlockCreator.Builder builder) {
			this.builder = builder;
		}

		public PottedPlantPack build(ModData modData) {
			return new PottedPlantPack(modData, builder);
		}
	}

	public Block getPlant() {
		return this.plant;
	}

	public Block getPottedPlant() {
		return this.pottedPlant;
	}
}