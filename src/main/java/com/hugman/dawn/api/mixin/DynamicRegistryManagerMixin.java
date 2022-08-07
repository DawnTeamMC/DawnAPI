package com.hugman.dawn.api.mixin;

import com.google.common.collect.ImmutableMap;
import com.hugman.dawn.api.DawnRegistries;
import com.hugman.dawn.api.init.shape.ConfiguredShape;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DynamicRegistryManager.class)
public interface DynamicRegistryManagerMixin {
	@Shadow
	private static <E> void register(ImmutableMap.Builder<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> infosBuilder, RegistryKey<? extends Registry<E>> registryRef, Codec<E> entryCodec) {}

	@SuppressWarnings("InvalidInjectorMethodSignature")
	@ModifyVariable(method = "method_30531", at = @At("STORE"))
	private static ImmutableMap.Builder<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> addInfos(ImmutableMap.Builder<RegistryKey<? extends Registry<?>>, DynamicRegistryManager.Info<?>> builder) {
		register(builder, DawnRegistries.CONFIGURED_SHAPE.getKey(), ConfiguredShape.CODEC);
		return builder;
	}
}
