package fr.hugman.dawn.mixin;

import fr.hugman.dawn.item.DawnItemSettings;
import fr.hugman.dawn.item.IDISHolder;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemMixin implements IDISHolder {
	private DawnItemSettings dawnSettings;

	@Inject(method = "<init>", at = @At("RETURN"))
	public void dawn$appendDawnSettings(Item.Settings settings, CallbackInfo ci) {
		if(settings instanceof DawnItemSettings ds) {
			this.dawnSettings = ds;
		}
	}

	@Override
	public DawnItemSettings getDawnSettings() {
		return dawnSettings;
	}
}
