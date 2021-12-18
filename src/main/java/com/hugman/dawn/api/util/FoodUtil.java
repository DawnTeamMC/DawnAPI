package com.hugman.dawn.api.util;

import com.hugman.dawn.api.object.item.DynamicFood;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;

public final class FoodUtil {
	public static int getHunger(ItemStack stack) {
		if(stack.getItem() instanceof DynamicFood dynamicFood) {
			return dynamicFood.getHunger(stack);
		}
		else {
			FoodComponent foodComponent = stack.getItem().getFoodComponent();
			if(foodComponent != null) return foodComponent.getHunger();
		}
		return 0;
	}

	public static float getSaturationPoints(ItemStack stack) {
		if(stack.getItem() instanceof DynamicFood dynamicFood) {
			return dynamicFood.getSaturationModifier(stack);
		}
		else {
			FoodComponent foodComponent = stack.getItem().getFoodComponent();
			if(foodComponent != null) return foodComponent.getSaturationModifier();
		}
		return 0;
	}
}
