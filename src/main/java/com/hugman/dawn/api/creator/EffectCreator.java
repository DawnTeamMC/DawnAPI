package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.registry.Registry;

public class EffectCreator extends Creator<StatusEffect> {
	protected final StatusEffect baseEffect;

	private EffectCreator(String name, StatusEffect baseEffect) {
		super(name);
		this.baseEffect = baseEffect;
	}

	@Override
	public StatusEffect register(ModData modData) {
		value = Registry.register(Registry.STATUS_EFFECT, modData.id(name), baseEffect);
		return value;
	}

	public static class Builder implements CreatorBuilder {
		protected final String name;
		protected final StatusEffect baseEffect;

		/**
		 * Creates an effect.
		 *
		 * @param name       The name of the effect.
		 * @param baseEffect The effect itself.
		 */
		public Builder(String name, StatusEffect baseEffect) {
			this.name = name;
			this.baseEffect = baseEffect;
		}

		public EffectCreator build() {
			return new EffectCreator(this.name, this.baseEffect);
		}
	}
}
