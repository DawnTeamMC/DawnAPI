package com.hugman.dawn.mod.mixin;

import com.hugman.dawn.mod.init.DawnEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin {
	@Redirect(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffect;canApplyUpdateEffect(II)Z"))
	public boolean dawn_damage(StatusEffect effect, int duration, int amplifier, LivingEntity entity, Runnable overwriteCallback) {
		if(effect == StatusEffects.POISON || effect == StatusEffects.WITHER) {
			StatusEffectInstance poisonResistanceInstance = entity.getStatusEffect(DawnEffects.POISON_RESISTANCE);
			if(poisonResistanceInstance != null) {
				int seconds = (effect == StatusEffects.POISON ? 25 : 40) * (poisonResistanceInstance.getAmplifier() + 2);
				int j = seconds >> amplifier;
				if (j > 0) {
					return duration % j == 0;
				}
				return true;
			}
		}
		return effect.canApplyUpdateEffect(duration, amplifier);
	}
}
