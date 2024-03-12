package fr.hugman.dawn.mixin;

import fr.hugman.dawn.item.settings.DawnItemInternals;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
abstract class ItemMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstruct(Item.Settings settings, CallbackInfo info) {
        DawnItemInternals.onBuild(settings, (Item) (Object) this);
    }
}
