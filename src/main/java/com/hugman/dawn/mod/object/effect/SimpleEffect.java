package com.hugman.dawn.mod.object.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class SimpleEffect extends StatusEffect {
	public SimpleEffect(StatusEffectCategory category, int liquidColor) {
		super(category, liquidColor);
	}
}