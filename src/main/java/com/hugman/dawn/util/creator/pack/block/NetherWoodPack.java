package com.hugman.dawn.util.creator.pack.block;

import com.hugman.dawn.util.creator.pack.Pack;
import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class NetherWoodPack extends WoodPack {
	protected final String suffix;
	protected final Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> supplier;

	private FungusPack fungusPack;

	public NetherWoodPack(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> supplier, MaterialColor planksColor, MaterialColor barkColor) {
		this(suffix, supplier, planksColor, planksColor, barkColor);
	}

	public NetherWoodPack(String suffix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> supplier, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
		super(suffix, planksColor, insideColor, barkColor, true);
		this.suffix = suffix;
		this.supplier = supplier;
	}

	@Override
	public Pack<Block> build() {
		fungusPack = (FungusPack) add(new FungusPack(suffix, supplier));
		return super.build();
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
