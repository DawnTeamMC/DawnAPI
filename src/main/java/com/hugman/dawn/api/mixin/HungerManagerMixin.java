package com.hugman.dawn.api.mixin;

import com.hugman.dawn.api.object.item.DynamicFood;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HungerManager.class)
public abstract class HungerManagerMixin {
	@Shadow
	public abstract void add(int food, float saturationModifier);

	@Inject(method = "eat", at = @At(value = "HEAD"), cancellable = true)
	public void dawn$eat(Item item, ItemStack stack, CallbackInfo ci) {
		if(item instanceof DynamicFood dynamicFood) {
			this.add(dynamicFood.getHunger(stack), dynamicFood.getSaturationModifier(stack));
			ci.cancel();
		}
	}
}
