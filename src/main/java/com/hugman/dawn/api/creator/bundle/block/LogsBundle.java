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
	private final BlockCreator log, strippedLog, wood, strippedWood;

	/**
	 * Bundle builder that contains a log, a wood block and their stripped variants.
	 *
	 * @param name        the name of the wood type (ex: <code>oak</code>)
	 * @param insideColor the material color of the inside of logs
	 * @param barkColor   the material color of the bark side of logs
	 * @param isNether    defines if the wood type comes from the nether (changes the name, sounds and materials)
	 */
	protected LogsBundle(String name, MapColor insideColor, MapColor barkColor, boolean isNether) {
		String logName = isNether ? "_stem" : "_log";
		String woodName = isNether ? "_hyphae" : "_wood";
		int flammability = isNether ? 0 : 5;

		BlockCreator.Builder builder = new BlockCreator.Builder().provider(PillarBlock::new).itemGroup(ItemGroup.BUILDING_BLOCKS).flammability(flammability);
		this.log = put(builder.copy(name + logName).settings(createMainLogSettings(isNether, insideColor, barkColor)).build());
		this.strippedLog = put(builder.copy("stripped_" + name + logName).settings(createLogSettings(isNether, insideColor)).build());
		this.wood = put(builder.copy(name + woodName).settings(createLogSettings(isNether, barkColor)).build());
		this.strippedWood = put(builder.copy("stripped_" + name + woodName).settings(createLogSettings(isNether, insideColor)).build());

		StrippableBlockRegistry.register(getLog(), getStrippedLog());
		StrippableBlockRegistry.register(getWood(), getStrippedWood());
	}

	private static AbstractBlock.Settings createMainLogSettings(boolean isNether, MapColor insideColor, MapColor barkColor) {
		return FabricBlockSettings.of(isNether ? Material.NETHER_WOOD : Material.WOOD, (blockState) -> blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? insideColor : barkColor).strength(2.0F).sounds(isNether ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD);
	}

	private AbstractBlock.Settings createLogSettings(boolean isNether, MapColor color) {
		return (isNether ? DefaultBlockSettings.STEM : DefaultBlockSettings.LOG).mapColor(color);
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
