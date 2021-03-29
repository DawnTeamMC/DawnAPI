package com.hugman.dawn.mod.util.debug.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EnchantmentEntryData extends EntryData {
	@Expose
	protected List<EnchantmentData> entries;

	public EnchantmentEntryData(String namespace, Set<Identifier> set) {
		super(namespace, Registry.ENCHANTMENT);
		this.entries = new ArrayList<>();
		for(Identifier enchantmentId : set) {
			this.entries.add(new EnchantmentData(enchantmentId, Registry.ENCHANTMENT.get(enchantmentId)));
		}
	}

	public static class EnchantmentData {
		@Expose
		protected Identifier name;
		@Expose
		protected Properties properties;

		public EnchantmentData(Identifier ID, Enchantment enchantment) {
			this.name = ID;
			this.properties = new Properties(enchantment);
		}

		public static class Properties {
			@Expose
			protected int rarity;
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
				this.rarity = enchantment.getRarity().getWeight();
				this.maxLevel = enchantment.getMaxLevel();
				this.minLevel = enchantment.getMinLevel();
				this.isTreasure = enchantment.isTreasure();
				this.isCursed = enchantment.isCursed();
				this.isAvailableForEnchantedBookOffer = enchantment.isAvailableForEnchantedBookOffer();
				this.isAvailableForRandomSelection = enchantment.isAvailableForRandomSelection();
			}
		}
	}
}
