package com.hugman.dawn.mod.mixin;

import com.hugman.dawn.api.util.EnchantmentUtil;
import com.hugman.dawn.mod.init.DawnEnchantments;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Redirect(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;appendEnchantments(Ljava/util/List;Lnet/minecraft/nbt/ListTag;)V"))
	public void dawn_appendEnchantments(List<Text> tooltip, ListTag enchantments, @Nullable PlayerEntity playerEntity, TooltipContext context) {
		ItemStack stack = (ItemStack) (Object) this;
		if(EnchantmentUtil.hasEnchantment(DawnEnchantments.IGNORANCE_CURSE, stack)) {
			if(playerEntity != null) {
				if(playerEntity.isCreative()) {
					ItemStack.appendEnchantments(tooltip, enchantments);
					return;
				}
			}
			tooltip.add(DawnEnchantments.IGNORANCE_CURSE.getName(EnchantmentHelper.getLevel(DawnEnchantments.IGNORANCE_CURSE, stack)));
		}
		else {
			ItemStack.appendEnchantments(tooltip, enchantments);
		}
	}

	@Redirect(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isDamaged()Z", ordinal = 0))
	public boolean dawn_isDamaged(ItemStack stack) {
		ClientPlayerEntity clientPlayerEntity = MinecraftClient.getInstance().player;
		if(EnchantmentUtil.hasEnchantment(DawnEnchantments.IGNORANCE_CURSE, stack) && !clientPlayerEntity.isCreative()) {
			return false;
		}
		return stack.isDamaged();
	}
}
