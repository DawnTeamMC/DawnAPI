package fr.hugman.dawn.mixin;

import fr.hugman.dawn.block.settings.DawnBlockSettings;
import fr.hugman.dawn.item.settings.DawnItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractBlock.Settings.class)
public class BlockSettingsMixin implements DawnBlockSettings {
}
