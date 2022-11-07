package com.hugman.dawn.api.mixin;

import com.google.common.collect.ImmutableMap;
import com.hugman.dawn.api.DawnRegistries;
import com.hugman.dawn.api.init.shape.ConfiguredShape;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SerializableRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SerializableRegistries.class)
public abstract class DynamicRegistryManagerMixin {
	@Shadow public static <E> void add(ImmutableMap.Builder<RegistryKey<Registry<?>>, SerializableRegistries.Info<?>> builder, RegistryKey<? extends Registry<E>> key, Codec<E> entryCodec) {}
	@Unique static private boolean added = false;

	@SuppressWarnings("InvalidInjectorMethodSignature")
	@Inject(method = "add", at = @At("RETURN"))
	private static <E> void addInfos(ImmutableMap.Builder<RegistryKey<Registry<?>>, SerializableRegistries.Info<?>> builder, RegistryKey<? extends Registry<E>> key, Codec<E> entryCodec) {
		if (!added) { added = true; add(builder, DawnRegistries.CONFIGURED_SHAPE.getKey(), ConfiguredShape.CODEC); };
	}
}
