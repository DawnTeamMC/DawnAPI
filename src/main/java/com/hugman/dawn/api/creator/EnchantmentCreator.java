package com.hugman.dawn.api.creator;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing an enchantment to be created.
 */
public class EnchantmentCreator extends SimpleCreator<Enchantment>
{

	/**
	 * Creates an enchantment.
	 *
	 * @param name        the name of the enchantment
	 * @param enchantment the enchantment itself
	 */
	public EnchantmentCreator(String name, Enchantment enchantment) {
		super(Registry.ENCHANTMENT, name, enchantment);
	}
}
