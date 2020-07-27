package com.hugman.dawn.mixin;

import com.hugman.dawn.util.debug.EntryDebugWriter;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	@Inject(method = "init", at = @At(value = "TAIL"))
	private void dawnInit(CallbackInfo info) {
		EntryDebugWriter.init();
	}
}
