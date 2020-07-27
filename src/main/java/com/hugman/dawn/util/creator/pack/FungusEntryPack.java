package com.hugman.dawn.util.creator.pack;

import com.hugman.dawn.object.block.FungusBlock;
import com.hugman.dawn.util.creator.BlockCreator.Builder;
import com.hugman.dawn.util.creator.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class FungusEntryPack extends PottedPlantEntryPack {
	/**
	 * Creates an entry pack containing a fungus entry and its potted variant.
	 *
	 * @param suffix   The suffix of the fungus plant.
	 * @param supplier The supplier for the huge fungus feature.
	 */
	public FungusEntryPack(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> supplier) {
		super(new Builder(suffix + "_fungus", new FungusBlock(BlockSettings.FUNGUS, supplier)).build());
	}

	public Block getFungus() {
		return getPlant();
	}
}
