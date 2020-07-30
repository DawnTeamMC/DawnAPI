package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.FungusBlock;
import com.hugman.dawn.api.util.BlockSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class FungusPack {
	public static class Builder extends PottedPlantPack.Builder {
		/**
		 * Creates an entry pack containing a fungus entry and its potted variant.
		 *
		 * @param name               The name of the fungus plant. (ex: <code>crimson</code>)
		 * @param hugeFungusSupplier The supplier for the huge fungus feature.
		 */
		public Builder(String name, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier) {
			super(new BlockCreator.Builder(name + "_fungus", new FungusBlock(BlockSettings.FUNGUS, hugeFungusSupplier)));
		}
	}
}
