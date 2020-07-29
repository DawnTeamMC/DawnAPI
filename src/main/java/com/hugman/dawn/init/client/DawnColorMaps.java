package com.hugman.dawn.init.client;

import com.hugman.dawn.init.DawnBlockPack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;

@Environment(EnvType.CLIENT)
public class DawnColorMaps {
	public static void registerColors() {
		registerBlockColors();
		registerItemColors();
	}

	private static void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return FoliageColors.getSpruceColor();
		}, DawnBlockPack.SPRUCE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return FoliageColors.getBirchColor();
		}, DawnBlockPack.BIRCH_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return pos != null && world != null ? BiomeColors.getFoliageColor(pos, world) : FoliageColors.getDefaultColor();
		}, DawnBlockPack.OAK_LEAF_PILE, DawnBlockPack.JUNGLE_LEAF_PILE, DawnBlockPack.ACACIA_LEAF_PILE, DawnBlockPack.DARK_OAK_LEAF_PILE);
	}

	private static void registerItemColors() {
		ColorProviderRegistry.ITEM.register((item, layer) ->
		{
			return GrassColors.getColor(0.5D, 1.0D);
		}, DawnBlockPack.OAK_LEAF_PILE, DawnBlockPack.SPRUCE_LEAF_PILE, DawnBlockPack.BIRCH_LEAF_PILE, DawnBlockPack.JUNGLE_LEAF_PILE, DawnBlockPack.ACACIA_LEAF_PILE, DawnBlockPack.DARK_OAK_LEAF_PILE);
	}
}
