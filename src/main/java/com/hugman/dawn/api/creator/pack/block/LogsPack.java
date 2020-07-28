package com.hugman.dawn.api.creator.pack.block;

import com.hugman.dawn.api.object.item.AxeItem;
import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.BlockCreator.Builder;
import com.hugman.dawn.api.creator.BlockSettings;
import com.hugman.dawn.api.creator.pack.Pack;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;

public class LogsPack extends Pack<Block> {
	private final BlockCreator log;
	private final BlockCreator strippedLog;
	private final BlockCreator wood;
	private final BlockCreator strippedWood;

	private final String logSuffix;
	private final String woodSuffix;
	private final MaterialColor insideColor;
	private final MaterialColor barkColor;
	private final boolean isNether;

	/**
	 * Creates a creator pack containing a log, a wood block and their stripped variants.
	 *
	 * @param suffix      The suffix of the wood type. (ex: <code>oak</code>)
	 * @param insideColor The material color of the inside of logs.
	 * @param barkColor   The material color of the bark side of logs.
	 * @param isNether    Defines if the wood type comes from the nether. (changes the name, sounds and materials)
	 */
	public LogsPack(String suffix, MaterialColor insideColor, MaterialColor barkColor, boolean isNether) {
		this.logSuffix = isNether ? "_stem" : "_log";
		this.woodSuffix = isNether ? "_hyphae" : "_wood";
		this.insideColor = insideColor;
		this.barkColor = barkColor;
		this.isNether = isNether;
		this.log = add(new Builder(suffix + logSuffix, createLog()).copy(isNether ? Blocks.CRIMSON_STEM : Blocks.OAK_LOG));
		this.strippedLog = add(new Builder("stripped_" + suffix + logSuffix, createLog(false)).copy(isNether ? Blocks.STRIPPED_CRIMSON_STEM : Blocks.STRIPPED_OAK_LOG));
		this.wood = add(new Builder(suffix + woodSuffix, createLog(true)).copy(isNether ? Blocks.CRIMSON_HYPHAE : Blocks.OAK_WOOD));
		this.strippedWood = add(new Builder("stripped_" + suffix + woodSuffix, createLog(false)).copy(isNether ? Blocks.STRIPPED_CRIMSON_HYPHAE : Blocks.STRIPPED_OAK_WOOD));
		AxeItem.BLOCK_STRIPPING_MAP.put(getLog(), getStrippedLog());
		AxeItem.BLOCK_STRIPPING_MAP.put(getWood(), getStrippedWood());

	}

	protected Block createLog(boolean isSideBark) {
		FabricBlockSettings settings = isNether ? BlockSettings.STEM : BlockSettings.LOG;
		return new PillarBlock(settings.materialColor(isSideBark ? barkColor : insideColor));
	}

	protected Block createLog() {
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
