package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FungusBlock;
import net.minecraft.block.MapColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class NetherWoodBundle extends WoodBundle
{
	private final PlantBundle fungusPack;

	protected NetherWoodBundle(String name, Supplier<RegistryEntry<ConfiguredFeature<HugeFungusFeatureConfig, ?>>> hugeFungusSupplier, MapColor planksColor, MapColor insideColor, MapColor barkColor) {
		super(name, planksColor, insideColor, barkColor, true);
		this.fungusPack = put(new PlantBundle(new BlockCreator.Builder(name + "_fungus", settings -> new FungusBlock(settings, hugeFungusSupplier), DefaultBlockSettings.FUNGUS).compostingChance(0.65F).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT_MIPPED)));
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

	public Block getFungus() {
		return fungusPack.getPlant();
	}

	public Block getPottedFungus() {
		return fungusPack.getPottedPlant();
	}

	public static class Builder
	{
		private final String prefix;
		private final Supplier<RegistryEntry<ConfiguredFeature<HugeFungusFeatureConfig, ?>>> hugeFungusSupplier;
		private final MapColor planksColor;
		private final MapColor insideColor;
		private final MapColor barkColor;

		/**
		 * Bundle builder that contains blocks for nether wood types.
		 *
		 * @param prefix             the prefix of the wood type (ex: <code>crimson</code>)
		 * @param hugeFungusSupplier the supplier for the huge fungus feature
		 * @param woodColor          the material color of the wood
		 * @param barkColor          the material color of the bark side of stems
		 */
		public Builder(String prefix, Supplier<RegistryEntry<ConfiguredFeature<HugeFungusFeatureConfig, ?>>> hugeFungusSupplier, MapColor woodColor, MapColor barkColor) {
			this(prefix, hugeFungusSupplier, woodColor, woodColor, barkColor);
		}

		/**
		 * Bundle builder that contains blocks for nether wood types.
		 *
		 * @param prefix             the prefix of the wood type (ex: <code>crimson</code>)
		 * @param hugeFungusSupplier the supplier for the huge fungus feature
		 * @param planksColor        the material color of the planks
		 * @param insideColor        the material color of the inside of stems
		 * @param barkColor          the material color of the bark side of stems
		 */
		public Builder(String prefix, Supplier<RegistryEntry<ConfiguredFeature<HugeFungusFeatureConfig, ?>>> hugeFungusSupplier, MapColor planksColor, MapColor insideColor, MapColor barkColor) {
			this.prefix = prefix;
			this.hugeFungusSupplier = hugeFungusSupplier;
			this.planksColor = planksColor;
			this.insideColor = insideColor;
			this.barkColor = barkColor;
		}

		public NetherWoodBundle build() {
			return new NetherWoodBundle(prefix, hugeFungusSupplier, planksColor, insideColor, barkColor);
		}
	}
}
