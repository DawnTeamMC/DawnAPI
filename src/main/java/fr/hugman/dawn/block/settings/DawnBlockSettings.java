package fr.hugman.dawn.block.settings;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public interface DawnBlockSettings {
    /**
     * Makes the block burnable, sets the burning and spreading chances of this block.
     *
     * @see FireBlock#registerDefaultFlammables() Vanilla flammability values
     */
    default AbstractBlock.Settings burnable(int burn, int spread) {
        var extraData = DawnBlockInternals.extraData((AbstractBlock.Settings) this);
        extraData.flameBurn(burn);
        extraData.flameSpread(spread);
        return ((AbstractBlock.Settings) this).burnable();
    }

    default int getFlameBurn() {
        if (!DawnBlockInternals.hasExtraData((AbstractBlock.Settings) this)) {
            return 0;
        }
        return DawnBlockInternals.extraData((AbstractBlock.Settings) this).getFlameBurn();
    }

    default int getFlameSpread() {
        if (!DawnBlockInternals.hasExtraData((AbstractBlock.Settings) this)) {
            return 0;
        }
        return DawnBlockInternals.extraData((AbstractBlock.Settings) this).getFlameSpread();
    }

    /**
     * Sets the block that this block will be stripped into when using an axe.
     *
     * @see AxeItem Vanilla axe stripping values
     */
    default AbstractBlock.Settings stripsInto(Block block) {
        DawnBlockInternals.extraData((AbstractBlock.Settings) this).stripInto(block);
        return (AbstractBlock.Settings) this;
    }

    /**
     * Provides item settings for a {@code BlockItem} to be registered with this block.
     * <p>The item will be registered with the same identifier as the block.
     */
    default AbstractBlock.Settings item(Item.Settings itemSettings) {
        DawnBlockInternals.extraData((AbstractBlock.Settings) this).itemSettings(itemSettings);
        return (AbstractBlock.Settings) this;
    }

    /**
     * Provides empty item settings for a {@code BlockItem} to be registered with this block.
     * <p>The item will be registered with the same identifier as the block.
     */
    default AbstractBlock.Settings item() {
        return item(new Item.Settings());
    }
}
