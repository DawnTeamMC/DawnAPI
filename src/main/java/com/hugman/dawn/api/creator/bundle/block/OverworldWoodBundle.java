package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import com.hugman.dawn.api.util.DefaultBlockTemplates;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.ItemGroup;

import java.util.function.Predicate;

public class OverworldWoodBundle extends WoodBundle {
	private final PlantBundle saplingPack;
	private final BlockCreator leaves;

	protected OverworldWoodBundle(String prefix, SaplingGenerator saplingGenerator, Predicate<BlockState> saplingSoilPredicate, MapColor planksColor, MapColor insideColor, MapColor barkColor) {
		super(prefix, planksColor, insideColor, barkColor, false);
		this.saplingPack = put(new PlantBundle(new BlockCreator.Builder(prefix + "_sapling", settings -> new SaplingBlock(saplingGenerator, saplingSoilPredicate, settings), DefaultBlockSettings.SAPLING).compostingChance(0.3f).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT_MIPPED)));
		this.leaves = put(new BlockCreator.Builder().from(DefaultBlockTemplates.LEAVES).name(prefix + "_leaves").flammability(30, 60).compostingChance(0.3f).build());
	}

	public Block getSapling() {
		return saplingPack.getPlant();
	}

	public Block getPottedSapling() {
		return saplingPack.getPottedPlant();
	}

	public Block getLeaves() {
		return leaves.getValue();
	}

	public static class Builder {
		private final String prefix;
		private final SaplingGenerator saplingGenerator;
		private final MapColor planksColor;
		private final MapColor insideColor;
		private final MapColor barkColor;
		private Predicate<BlockState> saplingSoilPredicate;

		/**
		 * Creates an entry pack containing blocks for normal wood types.
		 *
		 * @param prefix           The prefix of the wood type. (ex: <code>crimson</code>)
		 * @param saplingGenerator The tree generator for the sapling.
		 * @param woodColor        The material color of the wood.
		 * @param barkColor        The material color of the bark side of logs.
		 */
		public Builder(String prefix, SaplingGenerator saplingGenerator, MapColor woodColor, MapColor barkColor) {
			this(prefix, saplingGenerator, woodColor, woodColor, barkColor);
		}

		/**
		 * Creates an entry pack containing blocks for normal wood types.
		 *
		 * @param prefix           The prefix of the wood type. (ex: <code>crimson</code>)
		 * @param saplingGenerator The tree generator for the sapling.
		 * @param planksColor      The material color of the planks.
		 * @param insideColor      The material color of the inside of logs.
		 * @param barkColor        The material color of the bark side of logs.
		 */
		public Builder(String prefix, SaplingGenerator saplingGenerator, MapColor planksColor, MapColor insideColor, MapColor barkColor) {
			this.prefix = prefix;
			this.saplingGenerator = saplingGenerator;
			this.planksColor = planksColor;
			this.insideColor = insideColor;
			this.barkColor = barkColor;
		}

		/**
		 * Adds a predicate for blocks that the sapling can grow on.
		 *
		 * @param predicate The predicate for the allowed states.
		 */
		public Builder saplingSoil(Predicate<BlockState> predicate) {
			this.saplingSoilPredicate = predicate;
			return this;
		}

		public OverworldWoodBundle build() {
			return new OverworldWoodBundle(prefix, saplingGenerator, saplingSoilPredicate, planksColor, insideColor, barkColor);
		}
	}
}
