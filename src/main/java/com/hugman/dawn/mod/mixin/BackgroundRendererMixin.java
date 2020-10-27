package com.hugman.dawn.mod.mixin;

import com.hugman.dawn.mod.init.DawnEffects;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
	@Redirect(method = "applyFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
	private static boolean dawn_hasStatusEffect(LivingEntity entity, StatusEffect effect, Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
		if(effect == StatusEffects.BLINDNESS && entity.hasStatusEffect(DawnEffects.FORESIGHT)) return false;
		else return entity.hasStatusEffect(effect);
	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
	private static boolean dawn_hasStatusEffect(LivingEntity entity, StatusEffect effect, Camera camera, float tickDelta, ClientWorld world, int i, float f) {
		if(effect == StatusEffects.BLINDNESS && entity.hasStatusEffect(DawnEffects.FORESIGHT)) return false;
		else return entity.hasStatusEffect(effect);
	}
}
