package com.hugman.dawn.api.object.item;

import net.minecraft.item.ToolMaterial;

public class PickaxeItem extends net.minecraft.item.PickaxeItem {
	/* Extension for internal publicity */
	public PickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}
}
