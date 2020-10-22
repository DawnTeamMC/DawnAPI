package com.hugman.dawn.mod.mixin;

import com.hugman.dawn.mod.init.DawnEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	@Redirect(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;eat(Lnet/minecraft/item/Item;Lnet/minecraft/item/ItemStack;)V"))
	public void dawn_eat(HungerManager hungerManager, Item item, ItemStack stack, World world) {
		PlayerEntity player = (PlayerEntity) (Object) this;
		StatusEffectInstance fulfillmentInstance = player.getStatusEffect(DawnEffects.FULFILLMENT);
		if(fulfillmentInstance != null) {
			if(item.isFood()) {
				FoodComponent foodComponent = item.getFoodComponent();
				hungerManager.add(foodComponent.getHunger(), (float) (foodComponent.getSaturationModifier() + (foodComponent.getSaturationModifier() * (fulfillmentInstance.getAmplifier() + 1) * 0.25)));
				return;
			}
		}
		hungerManager.eat(item, stack);
	}
}
