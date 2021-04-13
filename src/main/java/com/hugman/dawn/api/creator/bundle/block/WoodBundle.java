package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.DefaultBlockTemplate;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;

public class WoodBundle extends Bundle {
	private final BlockCreator planks;
	private final LogsBundle logs;
	private final MTBlockBundle woodenBlocks;
	private MTBlockBundle barkBlocks;

	protected WoodBundle(String prefix, MapColor planksColor, MapColor insideColor, MapColor barkColor, boolean isNether, boolean addBarkBlocks) {
		FabricBlockSettings settings = FabricBlockSettings.copyOf(isNether ? Blocks.CRIMSON_PLANKS : Blocks.OAK_PLANKS).mapColor(planksColor);
		BlockCreator.Builder builder = new BlockCreator.Builder().name(prefix).settings(FabricBlockSettings.copyOf(isNether ? Blocks.CRIMSON_PLANKS : Blocks.OAK_PLANKS).mapColor(planksColor));
		this.planks = put(builder.copy().applyTemplate(DefaultBlockTemplate.PLANKS).build());
		this.logs = put(new LogsBundle(prefix, insideColor, barkColor, isNether));
		this.woodenBlocks = put(new MTBlockBundle(builder, DefaultBlockTemplate.STAIRS, DefaultBlockTemplate.SLAB, DefaultBlockTemplate.TRAPDOOR, DefaultBlockTemplate.WOOD_PRESSURE_PLATE, DefaultBlockTemplate.WOOD_BUTTON, DefaultBlockTemplate.FENCE, DefaultBlockTemplate.FENCE_GATE, DefaultBlockTemplate.DOOR));
		if(addBarkBlocks) this.barkBlocks = put(new MTBlockBundle(builder.copy().name(prefix + logs.getWoodName()).settings(settings.mapColor(barkColor)), DefaultBlockTemplate.STAIRS, DefaultBlockTemplate.SLAB, DefaultBlockTemplate.WOOD_BUTTON));
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
		return woodenBlocks.getBlock(DefaultBlockTemplate.WOOD_PRESSURE_PLATE);
	}

	public Block getTrapdoor() {
		return woodenBlocks.getBlock(DefaultBlockTemplate.TRAPDOOR);
	}

	public Block getButton() {
		return woodenBlocks.getBlock(DefaultBlockTemplate.WOOD_BUTTON);
	}

	public Block getStairs() {
		return woodenBlocks.getBlock(DefaultBlockTemplate.STAIRS);
	}

	public Block getSlab() {
		return woodenBlocks.getBlock(DefaultBlockTemplate.SLAB);
	}

	public Block getFenceGate() {
		return woodenBlocks.getBlock(DefaultBlockTemplate.FENCE_GATE);
	}

	public Block getFence() {
		return woodenBlocks.getBlock(DefaultBlockTemplate.FENCE);
	}

	public Block getDoor() {
		return woodenBlocks.getBlock(DefaultBlockTemplate.DOOR);
	}

	public Block getWoodStairs() {
		return barkBlocks.getBlock(DefaultBlockTemplate.STAIRS);
	}

	public Block getWoodSlab() {
		return barkBlocks.getBlock(DefaultBlockTemplate.SLAB);
	}

	public Block getWoodButton() {
		return barkBlocks.getBlock(DefaultBlockTemplate.WOOD_BUTTON);
	}

	public static class Builder {
		private final String name;
		private final MapColor planksColor;
		private final MapColor insideColor;
		private final MapColor barkColor;
		private final boolean isNether;
		private boolean addBarkBlocks;

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
			this.addBarkBlocks = false;
		}

		public Builder barkBlocks(boolean barkBlocks) {
			this.addBarkBlocks = barkBlocks;
			return this;
		}

		public WoodBundle build() {
			return new WoodBundle(name, planksColor, insideColor, barkColor, isNether, addBarkBlocks);
		}
	}
}
