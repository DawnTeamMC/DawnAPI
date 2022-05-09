package com.hugman.dawn.mod.mixin;

import com.google.gson.JsonElement;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import net.minecraft.data.DataCache;
import net.minecraft.data.report.WorldgenProvider;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.nio.file.Path;

@Mixin(WorldgenProvider.class)
public interface WorldgenProviderAccessor {
    @Invoker(value = "writeRegistryEntries", remap = false)
    static <T> void dawn$invokeWriteRegistryEntries(DataCache cache, Path path, DynamicRegistryManager registryManager, DynamicOps<JsonElement> json, DynamicRegistryManager.Info<T> info) {
        throw new Error("Mixin did not apply");
    }

    @Invoker(value = "writeRegistryEntries", remap = false)
    static <E, T extends Registry<E>> void dawn$invokeWriteRegistryEntries(Path path, DataCache cache, DynamicOps<JsonElement> json, RegistryKey<? extends T> registryKey, T registry, Encoder<E> encoder) {
        throw new Error("Mixin did not apply");
    }
}