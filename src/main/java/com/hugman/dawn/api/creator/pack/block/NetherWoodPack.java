package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class NetherWoodPack extends WoodPack {
	private final PottedPlantPack fungusPack;

	protected NetherWoodPack(ModData modData, String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
		super(modData, suffix, planksColor, insideColor, barkColor, true);
		this.fungusPack = add(new FungusPack.Builder(suffix, hugeFungusSupplier), modData);
	}

	public static class Builder implements PackBuilder {
		private final String suffix;
		private final Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier;
		private final MaterialColor planksColor;
		private final MaterialColor insideColor;
		private final MaterialColor barkColor;

		/**
		 * Creates an entry pack containing blocks for nether wood types.
		 *
		 * @param suffix             The suffix of the wood type. (ex: <code>crimson</code>)
		 * @param hugeFungusSupplier The supplier for the huge fungus feature.
		 * @param woodColor          The material color of the wood.
		 * @param barkColor          The material color of the bark side of stems.
		 */
		public Builder(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier, MaterialColor woodColor, MaterialColor barkColor) {
			this(suffix, hugeFungusSupplier, woodColor, woodColor, barkColor);
		}

		/**
		 * Creates an entry pack containing blocks for nether wood types.
		 *
		 * @param suffix             The suffix of the wood type. (ex: <code>crimson</code>)
		 * @param hugeFungusSupplier The supplier for the huge fungus feature.
		 * @param planksColor        The material color of the planks.
		 * @param insideColor        The material color of the inside of stems.
		 * @param barkColor          The material color of the bark side of stems.
		 */
		public Builder(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
			this.suffix = suffix;
			this.hugeFungusSupplier = hugeFungusSupplier;
			this.planksColor = planksColor;
			this.insideColor = insideColor;
			this.barkColor = barkColor;
		}

		public NetherWoodPack build(ModData modData) {
			return new NetherWoodPack(modData, suffix, hugeFungusSupplier, planksColor, insideColor, barkColor);
		}
	}

	public Block getStem() {
		return getLog();
	}

	public Block getStrippedStem() {
		return getStrippedLog();
	}

	public Block getHyphae() {
		return getWood();
	}

	public Block getStrippedHyphae() {
		return getStrippedWood();
	}

	public Block getHyphaeStairs() {
		return getWoodStairs();
	}

	public Block getHyphaeSlab() {
		return getWoodSlab();
	}

	public Block getHyphaeVerticalSlab() {
		return getWoodVerticalSlab();
	}

	public Block getHyphaeButton() {
		return getWoodButton();
	}

	public Block getFungus() {
		return fungusPack.getPlant();
	}

	public Block getPottedFungus() {
		return fungusPack.getPottedPlant();
	}
}
