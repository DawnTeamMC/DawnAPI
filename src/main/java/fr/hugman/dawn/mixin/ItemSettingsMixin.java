package fr.hugman.dawn.mixin;

import fr.hugman.dawn.item.settings.DawnItemSettings;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.Settings.class)
public class ItemSettingsMixin implements DawnItemSettings {
}
