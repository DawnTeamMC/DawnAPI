package fr.hugman.dawn.item;

import net.minecraft.item.ItemStack;

/**
 * Interface used for food items that can have dynamic food values. The API will automatically add AppleSkin compatibility and restore correct food values upon consumption for items that implement this interface.
 */
public interface DynamicFood {
	int getHunger(ItemStack stack);

	float getSaturationModifier(ItemStack stack);
}