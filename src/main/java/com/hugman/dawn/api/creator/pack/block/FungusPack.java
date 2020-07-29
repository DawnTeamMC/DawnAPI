package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockSettings;
import com.hugman.dawn.api.object.block.FungusBlock;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class FungusPack {
	public static class Builder extends PottedPlantPack.Builder {
		/**
		 * Creates an entry pack containing a fungus entry and its potted variant.
		 *
		 * @param suffix             The suffix of the fungus plant.
		 * @param hugeFungusSupplier The supplier for the huge fungus feature.
		 */
		public Builder(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier) {
			super(new BlockCreator.Builder(suffix + "_fungus", new FungusBlock(BlockSettings.FUNGUS, hugeFungusSupplier)));
		}
	}
}
