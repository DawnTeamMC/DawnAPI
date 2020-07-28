package com.hugman.dawn.init.client;

import com.hugman.dawn.init.DawnBlockPack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.FoliageColors;

@Environment(EnvType.CLIENT)
public class DawnColorMaps {
	public static void registerBlockColors() {
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return FoliageColors.getSpruceColor();
		}, DawnBlockPack.SPRUCE_LEAF_PILE);
		ColorProviderRegistry.BLOCK.register((block, pos, world, layer) ->
		{
			return FoliageColors.getBirchColor();
		}, MubbleBlocks.BIRCH_LEAF_PILE);
	}
}
