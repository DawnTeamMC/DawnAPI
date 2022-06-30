package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.sapling.SaplingGenerator;

import java.util.function.Predicate;

public class OverworldWoodBundle extends WoodBundle
{
	private final PlantBundle saplingPack;
	private final BlockCreator leaves;

	protected OverworldWoodBundle(String name, SaplingGenerator saplingGenerator, Predicate<BlockState> saplingSoilPredicate, MapColor planksColor, MapColor insideColor, MapColor barkColor) {
		super(name, planksColor, insideColor, barkColor, false);
		this.saplingPack = put(new PlantBundle(DefaultBlockBuilders.SAPLING.copy(name + "_sapling").provider(s -> new SaplingBlock(saplingGenerator, saplingSoilPredicate, s))));
		this.leaves = put(DefaultBlockBuilders.LEAVES.copy(name + "_leaves").build());
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

	public static class Builder
	{
		private final String prefix;
		private final SaplingGenerator saplingGenerator;
		private final MapColor planksColor;
		private final MapColor insideColor;
		private final MapColor barkColor;
		private Predicate<BlockState> saplingSoilPredicate;

		/**
		 * Bundle builder that contains blocks for normal wood types.
		 *
		 * @param prefix           The prefix of the wood type. (ex: <code>oak</code>)
		 * @param saplingGenerator The tree generator for the sapling.
		 * @param woodColor        The material color of the wood.
		 * @param barkColor        The material color of the bark side of logs.
		 */
		public Builder(String prefix, SaplingGenerator saplingGenerator, MapColor woodColor, MapColor barkColor) {
			this(prefix, saplingGenerator, woodColor, woodColor, barkColor);
		}

		/**
		 * Bundle builder that contains blocks for normal wood types.
		 *
		 * @param prefix           the prefix of the wood type (ex: <code>oak</code>)
		 * @param saplingGenerator the tree generator for the sapling
		 * @param planksColor      the material color of the planks
		 * @param insideColor      the material color of the inside of logs
		 * @param barkColor        the material color of the bark side of logs
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
		 * @param predicate the predicate for the allowed states
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
