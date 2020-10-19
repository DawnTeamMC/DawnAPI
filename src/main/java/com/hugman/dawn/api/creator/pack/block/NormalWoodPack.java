package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.util.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.item.ItemGroup;

public class NormalWoodPack extends WoodPack {
	private final PottedPlantPack saplingPack;
	private final LeavesPack leavesPack;

	protected NormalWoodPack(ModData modData, String suffix, SaplingGenerator saplingGenerator, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
		super(modData, suffix, planksColor, insideColor, barkColor, false);
		this.saplingPack = add(new PottedPlantPack.Builder(new BlockCreator.Builder(suffix + "_sapling", new SaplingBlock(saplingGenerator, BlockSettings.SAPLING)).itemGroup(ItemGroup.DECORATIONS).render(BlockCreator.Render.CUTOUT_MIPPED)), modData);
		this.leavesPack = add(new LeavesPack.Builder(suffix), modData);
	}

	public static class Builder implements PackBuilder {
		private final String suffix;
		private final SaplingGenerator saplingGenerator;
		private final MaterialColor planksColor;
		private final MaterialColor insideColor;
		private final MaterialColor barkColor;

		/**
		 * Creates an entry pack containing blocks for normal wood types.
		 *
		 * @param suffix           The suffix of the wood type. (ex: <code>crimson</code>)
		 * @param saplingGenerator The tree generator for the sapling.
		 * @param woodColor        The material color of the wood.
		 * @param barkColor        The material color of the bark side of logs.
		 */
		public Builder(String suffix, SaplingGenerator saplingGenerator, MaterialColor woodColor, MaterialColor barkColor) {
			this(suffix, saplingGenerator, woodColor, woodColor, barkColor);
		}

		/**
		 * Creates an entry pack containing blocks for normal wood types.
		 *
		 * @param suffix           The suffix of the wood type. (ex: <code>crimson</code>)
		 * @param saplingGenerator The tree generator for the sapling.
		 * @param planksColor      The material color of the planks.
		 * @param insideColor      The material color of the inside of logs.
		 * @param barkColor        The material color of the bark side of logs.
		 */
		public Builder(String suffix, SaplingGenerator saplingGenerator, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
			this.suffix = suffix;
			this.saplingGenerator = saplingGenerator;
			this.planksColor = planksColor;
			this.insideColor = insideColor;
			this.barkColor = barkColor;
		}

		public NormalWoodPack build(ModData modData) {
			return new NormalWoodPack(modData, suffix, saplingGenerator, planksColor, insideColor, barkColor);
		}
	}

	public Block getSapling() {
		return saplingPack.getPlant();
	}

	public Block getPottedSapling() {
		return saplingPack.getPottedPlant();
	}

	public Block getLeaves() {
		return leavesPack.getLeaves();
	}

	public Block getLeafPile() {
		return leavesPack.getLeafPile();
	}
}
