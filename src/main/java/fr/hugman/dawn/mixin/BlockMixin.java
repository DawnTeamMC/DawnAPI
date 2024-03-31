package fr.hugman.dawn.mixin;

import fr.hugman.dawn.block.settings.DawnBlockInternals;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
abstract class BlockMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void onConstruct(Block.Settings settings, CallbackInfo info) {
        DawnBlockInternals.onBuild(settings, (Block) (Object) this);
    }
}
