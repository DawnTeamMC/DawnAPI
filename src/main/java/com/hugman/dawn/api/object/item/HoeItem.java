package com.hugman.dawn.api.object.item;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class HoeItem extends net.minecraft.item.HoeItem {
	/* Extension for internal publicity */
	public HoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}
}
