package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public class EnchantmentCreator extends Creator<Enchantment> {

	private EnchantmentCreator(String name, Enchantment enchantment, ModData modData) {
		super(modData, name, enchantment);
	}

	@Override
	public void register() {
		Registry.register(Registry.ENCHANTMENT, modData.id(name), value);
	}

	public static class Builder implements CreatorBuilder<Enchantment> {
		protected final String name;
		protected final Enchantment enchantment;

		/**
		 * Creates an enchantment.
		 *
		 * @param name            The name of the enchantment.
		 * @param enchantment The enchantment itself.
		 */
		public Builder(String name, Enchantment enchantment) {
			this.name = name;
			this.enchantment = enchantment;
		}

		public EnchantmentCreator build(ModData modData) {
			return new EnchantmentCreator(this.name, this.enchantment, modData);
		}
	}
}
