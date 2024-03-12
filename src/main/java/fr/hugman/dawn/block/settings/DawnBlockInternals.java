package fr.hugman.dawn.block.settings;

import fr.hugman.dawn.Registrar;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;

public class DawnBlockInternals {
    private static final WeakHashMap<AbstractBlock.Settings, ExtraData> extraData = new WeakHashMap<>();
    private static final WeakHashMap<Block, Item.Settings> itemData = new WeakHashMap<>();

    private DawnBlockInternals() {
    }

    public static ExtraData extraData(AbstractBlock.Settings settings) {
        return extraData.computeIfAbsent(settings, s -> new ExtraData());
    }

    public static boolean hasExtraData(AbstractBlock.Settings settings) {
        return extraData.containsKey(settings);
    }

    public static void onBuild(AbstractBlock.Settings settings, Block block) {
        ExtraData data = extraData.get(settings);

        if (data != null) {
            if (data.flameBurn > 0 && data.flameSpread > 0)
                FlammableBlockRegistry.getDefaultInstance().add(block, data.flameBurn, data.flameSpread);
            if (data.stripInto != null)
                StrippableBlockRegistry.register(block, data.stripInto);
            if(data.itemSettings != null)
                itemData.put(block, data.itemSettings);
        }
    }

    public static void onRegister(Identifier id, Block block) {
        Item.Settings settings = itemData.get(block);
        if (settings != null)
            Registrar.add(id, new BlockItem(block, settings));
    }

    public static final class ExtraData {
        private int flameBurn;
        private int flameSpread;
        @Nullable
        private Block stripInto;
        @Nullable
        private Item.Settings itemSettings;

        public void flameBurn(int burn) {
            this.flameBurn = burn;
        }

        public int getFlameBurn() {
            return flameBurn;
        }

        public void flameSpread(int spread) {
            this.flameSpread = spread;
        }

        public int getFlameSpread() {
            return flameSpread;
        }

        public void stripInto(Block block) {
            this.stripInto = block;
        }

        public void itemSettings(Item.Settings settings) {
            this.itemSettings = settings;
        }
    }
}
