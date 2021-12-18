package com.hugman.dawn.api.mixin;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.object.ModData;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DefaultedRegistry.class)
public class DefaultedRegistryMixin {
	@ModifyVariable(at = @At("HEAD"), method = "get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;", ordinal = 0)
	Identifier dawn$fixMissingFromRegistry(@Nullable Identifier id) {
		if(id != null) {
			for(ModData modData : Dawn.MOD_DATA_LIST) {
				boolean isFromOldVersion = modData.getOldModNames().contains(id.getNamespace());
				if(isFromOldVersion || id.getNamespace().equals(modData.getModName())) {
					if(modData.getOldObjectNames().containsKey(id.getPath())) {
						return new Identifier(modData.getModName(), modData.getOldObjectNames().get(id.getPath()));
					}
					else if(isFromOldVersion) {
						return new Identifier(modData.getModName(), id.getPath());
					}
				}
				else if(modData.getOldObjectIds().containsKey(id)) {
					return new Identifier(modData.getModName(), modData.getOldObjectNames().get(id.getPath()));
				}
			}
		}
		return id;
	}
}