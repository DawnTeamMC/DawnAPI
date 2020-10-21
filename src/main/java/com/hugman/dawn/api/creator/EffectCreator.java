package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;

public class EffectCreator extends Creator<StatusEffect> {
	private EffectCreator(ModData modData, String name, StatusEffect effect) {
		super(modData, name, effect);
	}

	@Override
	public void register() {
		Registry.register(Registry.STATUS_EFFECT, modData.id(name), value);
	}

	public static class Builder implements CreatorBuilder<StatusEffect> {
		protected final String name;
		protected final StatusEffect effect;

		/**
		 * Creates an effect.
		 *
		 * @param name   The name of the effect.
		 * @param effect The effect itself.
		 */
		public Builder(String name, StatusEffect effect) {
			this.name = name;
			this.effect = effect;
		}

		public EffectCreator build(ModData modData) {
			return new EffectCreator(modData, this.name, this.effect);
		}
	}
}
