package com.hugman.dawn.api.creator;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a status effect to be created.
 */
public class EffectCreator extends SimpleCreator<StatusEffect>
{

	/**
	 * Creates a status effect.
	 *
	 * @param name   the name of the status effect
	 * @param effect the status effect itself
	 */
	public EffectCreator(String name, StatusEffect effect) {
		super(Registry.STATUS_EFFECT, name, effect);
	}
}
