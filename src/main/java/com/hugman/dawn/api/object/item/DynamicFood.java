package com.hugman.dawn.api.object.item;

import net.minecraft.item.ItemStack;

public interface DynamicFood {
	int getHunger(ItemStack stack);
	float getSaturationModifier(ItemStack stack);
}