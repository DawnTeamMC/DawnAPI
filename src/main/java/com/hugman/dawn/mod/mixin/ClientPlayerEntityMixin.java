package com.hugman.dawn.mod.mixin;

import com.hugman.dawn.mod.init.DawnEffects;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
	private boolean dawn_tickMovementFix(ClientPlayerEntity entity, StatusEffect effect) {
		if(effect == StatusEffects.BLINDNESS && entity.hasStatusEffect(DawnEffects.FORESIGHT)) return false;
		else return entity.hasStatusEffect(effect);
	}

	@Redirect(method = "updateNausea", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
	private boolean dawn_nauseaFix(ClientPlayerEntity entity, StatusEffect effect) {
		if(effect == StatusEffects.NAUSEA && entity.hasStatusEffect(DawnEffects.FORESIGHT)) return false;
		else return entity.hasStatusEffect(effect);
	}
}
