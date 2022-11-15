package fr.hugman.dawn.mixin;

import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HungerManager.class)
public interface HungerManagerAccessor {
	@Accessor("saturationLevel")
	void setSaturationLevel(float saturationLevel);
}
