package com.hugman.dawn.mod.object.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class TelekinesisEnchantment extends Enchantment {
	public TelekinesisEnchantment(Rarity rarity, EquipmentSlot... slotTypes) {
		super(rarity, EnchantmentTarget.WEAPON, slotTypes);
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}

	@Override
	public int getMinPower(int enchantmentLevel) {
		return 25 + enchantmentLevel * 5;
	}

	@Override
	public int getMaxPower(int enchantmentLevel) {
		return this.getMinPower(enchantmentLevel) + 15;
	}
}
