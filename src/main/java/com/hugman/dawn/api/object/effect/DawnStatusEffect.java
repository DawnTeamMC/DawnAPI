package com.hugman.dawn.api.object.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class DawnStatusEffect extends StatusEffect {
	/* Extension for internal publicity */
	public DawnStatusEffect(StatusEffectCategory category, int liquidColor) {
		super(category, liquidColor);
	}
}