package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.SimpleBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public class EnchantmentCreator extends Creator<Enchantment> {
	protected final Enchantment baseEnchantment;

	private EnchantmentCreator(String name, Enchantment baseEnchantment) {
		super(name);
		this.baseEnchantment = baseEnchantment;
	}

	@Override
	public Enchantment register(ModData modData) {
		value = Registry.register(Registry.ENCHANTMENT, modData.id(name), baseEnchantment);
		return value;
	}

	public static class Builder implements SimpleBuilder {
		protected final String name;
		protected final Enchantment baseEnchantment;

		/**
		 * Creates an enchantment.
		 *
		 * @param name            The name of the enchantment.
		 * @param baseEnchantment The enchantment itself.
		 */
		public Builder(String name, Enchantment baseEnchantment) {
			this.name = name;
			this.baseEnchantment = baseEnchantment;
		}

		public EnchantmentCreator build() {
			return new EnchantmentCreator(this.name, this.baseEnchantment);
		}
	}
}
