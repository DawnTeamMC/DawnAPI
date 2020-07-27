package com.hugman.dawn.util.creator.pack.block;

import com.hugman.dawn.object.block.SaplingBlock;
import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockSettings;
import com.hugman.dawn.util.creator.pack.Pack;
import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.sapling.SaplingGenerator;

public class NormalWoodPack extends WoodPack {
	protected final String suffix;
	protected final SaplingGenerator saplingGenerator;

	private PottedPlantPack saplingPack;
	private LeavesPack leavesPack;

	public NormalWoodPack(String suffix, SaplingGenerator saplingGenerator, MaterialColor planksColor, MaterialColor barkColor) {
		this(suffix, saplingGenerator, planksColor, planksColor, barkColor);
	}

	public NormalWoodPack(String suffix, SaplingGenerator saplingGenerator, MaterialColor planksColor, MaterialColor insideColor, MaterialColor barkColor) {
		super(suffix, planksColor, insideColor, barkColor, false);
		this.suffix = suffix;
		this.saplingGenerator = saplingGenerator;
	}

	@Override
	public Pack<Block> build() {
		saplingPack = (PottedPlantPack) add(new PottedPlantPack(new BlockCreator.Builder(suffix + "_sapling", new SaplingBlock(saplingGenerator, BlockSettings.SAPLING))));
		leavesPack = (LeavesPack) add(new LeavesPack(suffix));
		return super.build();
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
