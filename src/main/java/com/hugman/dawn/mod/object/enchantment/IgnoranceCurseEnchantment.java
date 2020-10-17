package com.hugman.dawn.mod.object.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class IgnoranceCurseEnchantment extends Enchantment {
	public IgnoranceCurseEnchantment(Rarity rarity, EquipmentSlot... slotTypes) {
		super(rarity, EnchantmentTarget.BREAKABLE, slotTypes);
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	public int getMinPower(int enchantmentLevel) {
		return 25;
	}

	@Override
	public int getMaxPower(int enchantmentLevel) {
		return 50;
	}

	@Override
	public boolean isCursed() {
		return true;
	}

	@Override
	public boolean isTreasure() {
		return true;
	}
}
