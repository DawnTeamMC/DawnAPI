package com.hugman.dawn.api.creator;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public class EnchantmentCreator extends SimpleCreator<Enchantment> {
	public EnchantmentCreator(String name, Enchantment enchantment) {
		super(Registry.ENCHANTMENT, name, enchantment);
	}
}
