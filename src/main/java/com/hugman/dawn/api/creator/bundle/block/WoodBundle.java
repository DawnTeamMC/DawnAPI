package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.DefaultBlockTemplates;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;

public class WoodBundle extends Bundle {
	private final BlockCreator planks;
	private final LogsBundle logs;
	private final MTBlockBundle woodenBlocks;

	protected WoodBundle(String prefix, MapColor planksColor, MapColor insideColor, MapColor barkColor, boolean isNether) {
		FabricBlockSettings settings = FabricBlockSettings.copyOf(isNether ? Blocks.CRIMSON_PLANKS : Blocks.OAK_PLANKS).mapColor(planksColor);
		BlockCreator.Builder builder = new BlockCreator.Builder().name(prefix).settings(settings);
		this.planks = put(builder.copy().applyTemplate(DefaultBlockTemplates.PLANKS).build());
		this.logs = put(new LogsBundle(prefix, insideColor, barkColor, isNether));
		this.woodenBlocks = put(new MTBlockBundle(builder, DefaultBlockTemplates.STAIRS, DefaultBlockTemplates.SLAB, DefaultBlockTemplates.TRAPDOOR, DefaultBlockTemplates.WOOD_PRESSURE_PLATE, DefaultBlockTemplates.WOOD_BUTTON, DefaultBlockTemplates.FENCE, DefaultBlockTemplates.FENCE_GATE, DefaultBlockTemplates.DOOR));
	}

	public Block getPlanks() {
		return planks.getValue();
	}

	public Block getLog() {
		return logs.getLog();
	}

	public Block getStrippedLog() {
		return logs.getStrippedLog();
	}

	public Block getWood() {
		return logs.getWood();
	}

	public Block getStrippedWood() {
		return logs.getStrippedWood();
	}

	public Block getPressurePlate() {
		return woodenBlocks.getBlock(DefaultBlockTemplates.WOOD_PRESSURE_PLATE);
	}

	public Block getTrapdoor() {
		return woodenBlocks.getBlock(DefaultBlockTemplates.TRAPDOOR);
	}

	public Block getButton() {
		return woodenBlocks.getBlock(DefaultBlockTemplates.WOOD_BUTTON);
	}

	public Block getStairs() {
		return woodenBlocks.getBlock(DefaultBlockTemplates.STAIRS);
	}

	public Block getSlab() {
		return woodenBlocks.getBlock(DefaultBlockTemplates.SLAB);
	}

	public Block getFenceGate() {
		return woodenBlocks.getBlock(DefaultBlockTemplates.FENCE_GATE);
	}

	public Block getFence() {
		return woodenBlocks.getBlock(DefaultBlockTemplates.FENCE);
	}

	public Block getDoor() {
		return woodenBlocks.getBlock(DefaultBlockTemplates.DOOR);
	}

	public static class Builder {
		private final String name;
		private final MapColor planksColor;
		private final MapColor insideColor;
		private final MapColor barkColor;
		private final boolean isNether;

		/**
		 * Creates an entry pack containing blocks for basic wood types.
		 *
		 * @param name        The name of the wood type. (ex: <code>oak</code>)
		 * @param planksColor The material color of the planks.
		 * @param insideColor The material color of the inside of logs.
		 * @param barkColor   The material color of the bark side of logs.
		 * @param isNether    Defines if the wood type comes from the nether. (changes the name, sounds and materials)
		 */
		public Builder(String name, MapColor planksColor, MapColor insideColor, MapColor barkColor, boolean isNether) {
			this.name = name;
			this.planksColor = planksColor;
			this.insideColor = insideColor;
			this.barkColor = barkColor;
			this.isNether = isNether;
		}

		public WoodBundle build() {
			return new WoodBundle(name, planksColor, insideColor, barkColor, isNether);
		}
	}
}
