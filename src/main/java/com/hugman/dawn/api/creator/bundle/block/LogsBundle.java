package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.DefaultBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;

public class LogsBundle extends Bundle {
	private final BlockCreator log;
	private final BlockCreator strippedLog;
	private final BlockCreator wood;
	private final BlockCreator strippedWood;

	private final MapColor insideColor;
	private final MapColor barkColor;
	private final boolean isNether;

	/**
	 * Creates a creator pack containing a log, a wood block and their stripped variants.
	 *
	 * @param name        The name of the wood type. (ex: <code>oak</code>)
	 * @param insideColor The material color of the inside of logs.
	 * @param barkColor   The material color of the bark side of logs.
	 * @param isNether    Defines if the wood type comes from the nether. (changes the name, sounds and materials)
	 */
	protected LogsBundle(String name, MapColor insideColor, MapColor barkColor, boolean isNether) {
		String logName = isNether ? "_stem" : "_log";
		String woodName = isNether ? "_hyphae" : "_wood";
		this.insideColor = insideColor;
		this.barkColor = barkColor;
		this.isNether = isNether;
		int flammability = isNether ? 0 : 5;

		BlockCreator.Builder builder = new BlockCreator.Builder().provider(PillarBlock::new).itemGroup(ItemGroup.BUILDING_BLOCKS).flammability(flammability);
		this.log = put(builder.copy(name + logName).settings(createMainLogSettings()).build());
		this.strippedLog = put(builder.copy("stripped_" + name + logName).settings(createLogSettings(false)).build());
		this.wood = put(builder.copy(name + woodName).settings(createLogSettings(true)).build());
		this.strippedWood = put(builder.copy("stripped_" + name + woodName).settings(createLogSettings(false)).build());

		StrippableBlockRegistry.register(getLog(), getStrippedLog());
		StrippableBlockRegistry.register(getWood(), getStrippedWood());
	}

	private AbstractBlock.Settings createMainLogSettings() {
		return FabricBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, (blockState) -> blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? insideColor : barkColor).strength(2.0F).sounds(isNether ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD);
	}

	private AbstractBlock.Settings createLogSettings(boolean isSideBark) {
		return (isNether ? DefaultBlockSettings.STEM : DefaultBlockSettings.LOG).mapColor(isSideBark ? barkColor : insideColor);
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
}
