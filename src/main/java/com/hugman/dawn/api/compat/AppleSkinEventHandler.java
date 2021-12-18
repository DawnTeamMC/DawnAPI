package com.hugman.dawn.api.compat;

import com.hugman.dawn.api.object.item.DynamicFood;
import squeek.appleskin.api.AppleSkinApi;
import squeek.appleskin.api.event.FoodValuesEvent;
import squeek.appleskin.api.food.FoodValues;

public class AppleSkinEventHandler implements AppleSkinApi {
	@Override
	public void registerEvents() {
		FoodValuesEvent.EVENT.register(event -> {
			if(event.itemStack.getItem() instanceof DynamicFood dynamic) {
				FoodValues values = new FoodValues(dynamic.getHunger(event.itemStack), dynamic.getSaturationModifier(event.itemStack));
				event.defaultFoodValues = values;
				event.modifiedFoodValues = values;
			}
		});
	}
}