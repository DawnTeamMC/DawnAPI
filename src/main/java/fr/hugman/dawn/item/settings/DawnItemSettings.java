package fr.hugman.dawn.item.settings;

import net.minecraft.item.Item;

public interface DawnItemSettings {
    default Item.Settings fuelTime(int time) {
        DawnItemInternals.extraData((Item.Settings) this).fuelTime(time);
        return (Item.Settings) this;
    }

    default Item.Settings compostingChance(float chance) {
        DawnItemInternals.extraData((Item.Settings) this).compostingChance(chance);
        return (Item.Settings) this;
    }
}
