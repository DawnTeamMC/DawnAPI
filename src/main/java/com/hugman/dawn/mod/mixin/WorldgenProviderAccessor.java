package com.hugman.dawn.mod.mixin;

import com.google.gson.JsonElement;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataWriter;
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
    static <E, T extends Registry<E>> void dawn$invokeWriteRegistryEntries(DataWriter dataWriter, DynamicRegistryManager dynamicRegistryManager, DynamicOps<JsonElement> dynamicOps, DynamicRegistryManager.Info<T> info) {
        throw new Error("Mixin did not apply");
    }
}