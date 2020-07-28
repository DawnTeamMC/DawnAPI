package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.object.block.SaplingBlock;
import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.sapling.SaplingGenerator;

public class NormalWoodPack extends WoodPack {
	private final PottedPlantPack saplingPack;
	private final LeavesPack leavesPack;

	/**
	 * Creates an entry pack containing blocks for normal wood types.
	 *
	 * @param suffix           The suffix of the wood type. (ex: <code>crimson</code>)
	 * @param saplingGenerator The tree generator for the sapling.
	 * @param woodColor        The material color of the wood.
	 * @param barkColor        The material color of the bark side of logs.
	 */
	public NormalWoodPack(String suffix, SaplingGenerator saplingGenerator, MaterialColor woodColor, MaterialColor barkColor) {
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
	public NormalWoodPack(String suffix, SaplingGenerator saplingGenerator, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
		super(suffix, planksColor, insideColor, barkColor, false);
		this.saplingPack = add(new PottedPlantPack(new BlockCreator.Builder(suffix + "_sapling", new SaplingBlock(saplingGenerator, BlockSettings.SAPLING))));
		this.leavesPack = add(new LeavesPack(suffix));
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
