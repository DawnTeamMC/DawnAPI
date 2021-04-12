package com.hugman.dawn.api.creator;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;

public class EffectCreator extends SimpleCreator<StatusEffect> {
	public EffectCreator(String name, StatusEffect effect) {
		super(Registry.STATUS_EFFECT, name, effect);
	}
}
