package com.hugman.dawn.mod.mixin;

import net.minecraft.data.DataCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DataCache.CachedDataWriter.class)
public interface DataCacheAccessor
{
    @Invoker("<init>")
    static DataCache.CachedDataWriter dawn$init(String s, DataCache.CachedData data){
        throw new Error("Mixin did not apply!");
    }
}