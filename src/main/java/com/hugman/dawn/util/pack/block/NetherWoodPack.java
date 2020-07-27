package com.hugman.dawn.util.pack.block;

import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class NetherWoodPack extends WoodPack {
	private final FungusPack fungusCreator;

	public NetherWoodPack(String name, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> supplier, MaterialColor planksColor, MaterialColor barkColor) {
		this(name, supplier, planksColor, planksColor, barkColor);
	}

	public NetherWoodPack(String name, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> supplier, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
		super(name, planksColor, insideColor, barkColor, true);
		this.fungusCreator = add(new FungusPack(name, supplier));
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
		return fungusCreator.getFungus();
	}

	public Block getPottedFungus() {
		return fungusCreator.getPottedPlant();
	}
}
