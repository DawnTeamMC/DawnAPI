package com.hugman.dawn.util.pack.block;

import com.hugman.dawn.object.block.SaplingBlock;
import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.sapling.SaplingGenerator;

public class NormalWoodPack extends WoodPack {
	private final PottedPlantPack saplingPack;
	private final LeavesPack leavesPack;

	public NormalWoodPack(String name, SaplingGenerator saplingGenerator, MaterialColor planksColor, MaterialColor barkColor) {
		this(name, saplingGenerator, planksColor, planksColor, barkColor);
	}

	public NormalWoodPack(String name, SaplingGenerator saplingGenerator, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
		super(name, planksColor, insideColor, barkColor, false);
		this.saplingPack = add(new PottedPlantPack(new BlockCreator.Builder(name + "_sapling", new SaplingBlock(saplingGenerator, BlockSettings.SAPLING)).build()));
		this.leavesPack = add(new LeavesPack(name));
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
