package com.hugman.dawn.api.object.item;

import net.minecraft.item.ToolMaterial;

public class DawnAxeItem extends net.minecraft.item.AxeItem {
	/* Extension for internal publicity */
	public DawnAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}
}