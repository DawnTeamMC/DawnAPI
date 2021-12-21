package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.DefaultBlockBuilders;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;

public class WoodBundle extends Bundle {
	private final BlockCreator planks;
	private final LogsBundle logs;
	private final BlockCreator stairs, slab, trapdoor, pressurePlate, button, fence, fenceGate, door;

	protected WoodBundle(String name, MapColor planksColor, MapColor insideColor, MapColor barkColor, boolean isNether) {
		FabricBlockSettings settings = FabricBlockSettings.copyOf(isNether ? Blocks.CRIMSON_PLANKS : Blocks.OAK_PLANKS).mapColor(planksColor);
		BlockCreator.Builder builder = new BlockCreator.Builder().name(name).settings(settings);
		this.planks = put(builder.copy(name + "_planks").from(DefaultBlockBuilders.PLANKS).build());
		this.logs = put(new LogsBundle(name, insideColor, barkColor, isNether));
		this.stairs = put(builder.copy(name + "_stairs").from(DefaultBlockBuilders.STAIRS).build());
		this.slab = put(builder.copy(name + "_slab").from(DefaultBlockBuilders.SLAB).build());
		this.trapdoor = put(builder.copy(name + "_trapdoor").from(DefaultBlockBuilders.TRAPDOOR).build());
		this.pressurePlate = put(builder.copy(name + "_pressure_plate").from(DefaultBlockBuilders.WOOD_PRESSURE_PLATE).build());
		this.button = put(builder.copy(name + "_button").from(DefaultBlockBuilders.WOOD_BUTTON).build());
		this.fence = put(builder.copy(name + "_fence").from(DefaultBlockBuilders.FENCE).build());
		this.fenceGate = put(builder.copy(name + "_fence_gate").from(DefaultBlockBuilders.FENCE_GATE).build());
		this.door = put(builder.copy(name + "_door").from(DefaultBlockBuilders.DOOR).build());
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

	public Block getStairs() {
		return stairs.getValue();
	}

	public Block getSlab() {
		return slab.getValue();
	}

	public Block getTrapdoor() {
		return trapdoor.getValue();
	}

	public Block getPressurePlate() {
		return pressurePlate.getValue();
	}

	public Block getButton() {
		return button.getValue();
	}

	public Block getFenceGate() {
		return fenceGate.getValue();
	}

	public Block getFence() {
		return fence.getValue();
	}

	public Block getDoor() {
		return door.getValue();
	}

	public static class Builder {
		private final String name;
		private final MapColor planksColor;
		private final MapColor insideColor;
		private final MapColor barkColor;
		private final boolean isNether;

		/**
		 * Bundle builder that contains blocks for basic wood types.
		 *
		 * @param name        the name of the wood type. (ex: <code>oak</code>)
		 * @param planksColor the material color of the planks
		 * @param insideColor the material color of the inside of logs
		 * @param barkColor   the material color of the bark side of logs
		 * @param isNether    defines if the wood type comes from the nether (changes the name, sounds and materials)
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
