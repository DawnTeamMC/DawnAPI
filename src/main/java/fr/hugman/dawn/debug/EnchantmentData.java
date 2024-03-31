package fr.hugman.dawn.debug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;

public class EnchantmentData {
    @Expose
    protected Identifier name;
    @Expose
    protected Properties properties;

    public EnchantmentData(Identifier id, Enchantment enchantment) {
        this.name = id;
        this.properties = new Properties(enchantment);
    }

    public static class Properties {
        @Expose
        @SerializedName("max_level")
        protected int maxLevel;
        @Expose
        @SerializedName("min_level")
        protected int minLevel;
        @Expose
        @SerializedName("is_treasure")
        protected boolean isTreasure;
        @Expose
        @SerializedName("is_cursed")
        protected boolean isCursed;
        @Expose
        @SerializedName("is_available_for_enchanted_book_offer")
        protected boolean isAvailableForEnchantedBookOffer;
        @Expose
        @SerializedName("is_available_for_random_selection")
        protected boolean isAvailableForRandomSelection;

        public Properties(Enchantment enchantment) {
            this.maxLevel = enchantment.getMaxLevel();
            this.minLevel = enchantment.getMinLevel();
            this.isTreasure = enchantment.isTreasure();
            this.isCursed = enchantment.isCursed();
            this.isAvailableForEnchantedBookOffer = enchantment.isAvailableForEnchantedBookOffer();
            this.isAvailableForRandomSelection = enchantment.isAvailableForRandomSelection();
        }
    }
}
