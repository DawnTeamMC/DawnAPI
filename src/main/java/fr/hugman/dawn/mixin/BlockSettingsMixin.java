package fr.hugman.dawn.mixin;

import fr.hugman.dawn.block.settings.DawnBlockInternals;
import fr.hugman.dawn.block.settings.DawnBlockSettings;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.Settings.class)
public class BlockSettingsMixin implements DawnBlockSettings {
    @Inject(method = "copy(Lnet/minecraft/block/AbstractBlock;)Lnet/minecraft/block/AbstractBlock$Settings;", at = @At("RETURN"))
    private static void dawn$copy(AbstractBlock block, CallbackInfoReturnable<AbstractBlock.Settings> info) {
        var settings = block.getSettings();
        if (DawnBlockInternals.hasExtraData(settings)) {
            var sourceExtraData = DawnBlockInternals.extraData(settings);
            if (sourceExtraData.getFlameBurn() > 0 && sourceExtraData.getFlameSpread() > 0) {
                info.getReturnValue().burnable(sourceExtraData.getFlameBurn(), sourceExtraData.getFlameSpread());
            }
            info.getReturnValue().stripsInto(sourceExtraData.getStripInto());
            info.getReturnValue().item(sourceExtraData.getItemSettings());
        }
    }
}
