package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.util.ModData;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.util.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.util.registry.Registry;

public class PottedPlantPack extends Pack {
	private final Block plant;
	private final Block pottedPlant;

	protected PottedPlantPack(ModData modData, BlockCreator.Builder builder) {
		this.plant = add(builder, modData);
		this.pottedPlant = add(new BlockCreator.Builder("potted_" + Registry.BLOCK.getId(plant).getPath(), new FlowerPotBlock(getPlant(), BlockSettings.POTTED_PLANT.lightLevel(getPlant().getDefaultState().getLuminance()))).render(BlockCreator.Render.CUTOUT).noItem(), modData);
	}

	public static class Builder implements PackBuilder {
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