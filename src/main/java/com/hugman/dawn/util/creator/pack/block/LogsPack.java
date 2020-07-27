package com.hugman.dawn.util.creator.pack.block;

import com.hugman.dawn.object.item.AxeItem;
import com.hugman.dawn.util.creator.block.BlockCreator;
import com.hugman.dawn.util.creator.block.BlockCreator.Builder;
import com.hugman.dawn.util.creator.block.BlockSettings;
import com.hugman.dawn.util.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;

public class LogsPack extends Pack.Builder<Block> {
	protected final String suffix;
	protected final MaterialColor insideColor;
	protected final MaterialColor barkColor;
	protected final boolean isNether;
	protected final String logSuffix;
	protected final String woodSuffix;

	private BlockCreator log;
	private BlockCreator strippedLog;
	private BlockCreator wood;
	private BlockCreator strippedWood;

	/**
	 * Creates a creator pack containing a log, a wood block and their stripped variants.
	 *
	 * @param suffix      The suffix of the leaves.
	 * @param insideColor The inside color of the log block.
	 * @param barkColor   The bark color of the log block.
	 * @param isNether    Defines if the the blocks are used for nether trees. (changes the name, sounds and materials)
	 */
	public LogsPack(String suffix, MaterialColor insideColor, MaterialColor barkColor, boolean isNether) {
		this.suffix = suffix;
		this.insideColor = insideColor;
		this.barkColor = barkColor;
		this.isNether = isNether;
		this.logSuffix = isNether ? "_stem" : "_log";
		this.woodSuffix = isNether ? "_hyphae" : "_wood";
	}

	@Override
	public Pack<Block> build() {
		this.log = add(new Builder(suffix + logSuffix, createLog(insideColor, barkColor)).copy(isNether ? Blocks.CRIMSON_STEM : Blocks.OAK_LOG));
		this.strippedLog = add(new Builder("stripped_" + suffix + logSuffix, createLog(insideColor)).copy(isNether ? Blocks.STRIPPED_CRIMSON_STEM : Blocks.STRIPPED_OAK_LOG));
		this.wood = add(new Builder(suffix + woodSuffix, createLog(barkColor)).copy(isNether ? Blocks.CRIMSON_HYPHAE : Blocks.OAK_WOOD));
		this.strippedWood = add(new Builder("stripped_" + suffix + woodSuffix, createLog(insideColor)).copy(isNether ? Blocks.STRIPPED_CRIMSON_HYPHAE : Blocks.STRIPPED_OAK_WOOD));
		AxeItem.BLOCK_STRIPPING_MAP.put(getLog(), getStrippedLog());
		AxeItem.BLOCK_STRIPPING_MAP.put(getWood(), getStrippedWood());
		return super.build();
	}

	protected Block createLog(MaterialColor color) {
		FabricBlockSettings settings = isNether ? BlockSettings.STEM : BlockSettings.LOG;
		return new PillarBlock(settings.materialColor(color));
	}

	protected Block createLog(MaterialColor insideColor, MaterialColor barkColor) {
		return new PillarBlock(AbstractBlock.Settings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, (blockState) -> {
			return blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? insideColor : barkColor;
		}).strength(2.0F).sounds(isNether ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD));
	}

	public Block getLog() {
		return log.getValue();
	}

	public Block getStrippedLog() {
		return strippedLog.getValue();
	}

	public Block getWood() {
		return wood.getValue();
	}

	public Block getStrippedWood() {
		return strippedWood.getValue();
	}

	protected String getLogSuffix() {
		return logSuffix;
	}

	protected String getWoodSuffix() {
		return woodSuffix;
	}
}
