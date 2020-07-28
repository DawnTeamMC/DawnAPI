package com.hugman.dawn.api.creator.pack.block;

import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class NetherWoodPack extends WoodPack {
	private final FungusPack fungusPack;

	/**
	 * Creates an entry pack containing blocks for nether wood types.
	 *
	 * @param suffix             The suffix of the wood type. (ex: <code>crimson</code>)
	 * @param hugeFungusSupplier The supplier for the huge fungus feature.
	 * @param woodColor          The material color of the wood.
	 * @param barkColor          The material color of the bark side of stems.
	 */
	public NetherWoodPack(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier, MaterialColor woodColor, MaterialColor barkColor) {
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
	public NetherWoodPack(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
		super(suffix, planksColor, insideColor, barkColor, true);
		this.fungusPack = add(new FungusPack(suffix, hugeFungusSupplier));
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
		return fungusPack.getFungus();
	}

	public Block getPottedFungus() {
		return fungusPack.getPottedPlant();
	}
}
