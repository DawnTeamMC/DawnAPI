package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.FungusBlock;
import com.hugman.dawn.api.util.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.ItemGroup;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;

import java.util.function.Supplier;

public class NetherWoodBundle extends WoodBundle {
	private final PlantBundle fungusPack;

	protected NetherWoodBundle(String prefix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier, MapColor planksColor, MapColor insideColor, MapColor barkColor, boolean addHyphaeBlocks) {
		super(prefix, planksColor, insideColor, barkColor, true, addHyphaeBlocks);
		this.fungusPack = put(new PlantBundle(new BlockCreator.Builder(prefix + "_fungus", settings -> new FungusBlock(settings, hugeFungusSupplier), BlockSettings.FUNGUS).compostingChance(0.65F).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT_MIPPED)));
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

	public Block getHyphaeStairs() {
		return getWoodStairs();
	}

	public Block getHyphaeSlab() {
		return getWoodSlab();
	}

	public Block getHyphaeButton() {
		return getWoodButton();
	}

	public static class Builder {
		private final String prefix;
		private final Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier;
		private final MapColor planksColor;
		private final MapColor insideColor;
		private final MapColor barkColor;
		private boolean addHyphaeBlocks;

		/**
		 * Creates an entry pack containing blocks for nether wood types.
		 *
		 * @param prefix             The prefix of the wood type. (ex: <code>crimson</code>)
		 * @param hugeFungusSupplier The supplier for the huge fungus feature.
		 * @param woodColor          The material color of the wood.
		 * @param barkColor          The material color of the bark side of stems.
		 */
		public Builder(String prefix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier, MapColor woodColor, MapColor barkColor) {
			this(prefix, hugeFungusSupplier, woodColor, woodColor, barkColor);
		}

		/**
		 * Creates an entry pack containing blocks for nether wood types.
		 *
		 * @param prefix             The prefix of the wood type. (ex: <code>crimson</code>)
		 * @param hugeFungusSupplier The supplier for the huge fungus feature.
		 * @param planksColor        The material color of the planks.
		 * @param insideColor        The material color of the inside of stems.
		 * @param barkColor          The material color of the bark side of stems.
		 */
		public Builder(String prefix, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> hugeFungusSupplier, MapColor planksColor, MapColor insideColor, MapColor barkColor) {
			this.prefix = prefix;
			this.hugeFungusSupplier = hugeFungusSupplier;
			this.planksColor = planksColor;
			this.insideColor = insideColor;
			this.barkColor = barkColor;
		}

		public Builder hyphaeBlocks(boolean hyphaeBlocks) {
			this.addHyphaeBlocks = hyphaeBlocks;
			return this;
		}

		public NetherWoodBundle build() {
			return new NetherWoodBundle(prefix, hugeFungusSupplier, planksColor, insideColor, barkColor, addHyphaeBlocks);
		}
	}
}
