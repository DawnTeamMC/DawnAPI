package com.hugman.dawn.mod.object.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.math.Vec3d;

public class HiccupEffect extends SimpleEffect {
	public HiccupEffect(StatusEffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
	}

	@Override
	public void applyUpdateEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		Vec3d baseMotion = entityLivingBaseIn.getVelocity();
		Vec3d finalMotion = new Vec3d(baseMotion.getX(), 0.2D, baseMotion.getZ());
		entityLivingBaseIn.setVelocity(finalMotion);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		int k = 200 >> amplifier;
		if(k > 0) {
			return duration % k == 0;
		}
		else {
			return true;
		}
	}
}