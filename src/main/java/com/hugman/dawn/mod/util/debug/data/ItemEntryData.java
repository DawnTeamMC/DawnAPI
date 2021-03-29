package com.hugman.dawn.mod.util.debug.data;

import com.google.gson.annotations.Expose;
import com.hugman.dawn.api.mixin.ItemAccessor;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemEntryData extends EntryData {
	@Expose
	protected List<ItemData> entries;

	public ItemEntryData(String namespace, Set<Identifier> set) {
		super(namespace, Registry.ITEM);
		this.entries = new ArrayList<>();
		for(Identifier itemID : set) {
			this.entries.add(new ItemData(itemID, Registry.ITEM.get(itemID)));
		}
	}

	public static class ItemData {
		@Expose
		protected Identifier name;
		@Expose
		protected Properties properties;

		public ItemData(Identifier ID, Item item) {
			this.name = ID;
			this.properties = new Properties(item);
		}

		public static class Properties {
			@Expose
			protected String group;
			@Expose
			protected Rarity rarity;
			@Expose
			protected int maxCount;
			@Expose
			protected int maxDamage;
			@Expose
			protected boolean fireproof;
			@Expose
			protected Identifier recipeRemainder;

			public Properties(Item item) {
				ItemAccessor accessor = (ItemAccessor) item;
				this.group = item.getGroup() != null ? item.getGroup().getName() : null;
				this.rarity = accessor.getRarity();
				this.maxCount = item.getMaxCount();
				this.maxDamage = item.getMaxDamage();
				this.fireproof = item.isFireproof();
				this.recipeRemainder = item.getRecipeRemainder() != null ? Registry.ITEM.getId(item.getRecipeRemainder()) : null;
			}
		}
	}
}
