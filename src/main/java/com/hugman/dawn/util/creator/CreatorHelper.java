package com.hugman.dawn.util.creator;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;

public class CreatorHelper {
	public static int getFlammabilityBurn(Block block) {
		return FlammableBlockRegistry.getDefaultInstance().get(block).getBurnChance();
	}

	public static int getFlammabilitySpread(Block block) {
		return FlammableBlockRegistry.getDefaultInstance().get(block).getSpreadChance();
	}

	public static int getCookTime(ItemConvertible item) {
		return FuelRegistry.INSTANCE.get(item);
	}
}
