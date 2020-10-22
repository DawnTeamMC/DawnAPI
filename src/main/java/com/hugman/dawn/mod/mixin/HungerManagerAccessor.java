package com.hugman.dawn.mod.mixin;

import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HungerManager.class)
public interface HungerManagerAccessor {
	@Accessor("foodSaturationLevel")
	void setFoodSaturationLevel(float foodSaturationLevel);
}
