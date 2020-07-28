package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.object.block.FungusBlock;
import com.hugman.dawn.api.creator.BlockCreator.Builder;
import com.hugman.dawn.api.creator.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class FungusPack extends PottedPlantPack {
	/**
	 * Creates an entry pack containing a fungus entry and its potted variant.
	 *
	 * @param suffix             The suffix of the fungus plant.
	 * @param hugeFungusSupplier The supplier for the huge fungus feature.
	 */
	public FungusPack(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier) {
		super(new Builder(suffix + "_fungus", new FungusBlock(BlockSettings.FUNGUS, hugeFungusSupplier)));
	}

	public Block getFungus() {
		return getPlant();
	}
}
