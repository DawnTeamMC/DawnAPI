package com.hugman.dawn.mod.util.data;

import com.google.gson.annotations.Expose;
import com.hugman.dawn.api.mixin.ItemAccessor;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemData
{
	@Expose
	protected Identifier name;
	@Expose
	protected Properties properties;

	public ItemData(Identifier id, Item item) {
		this.name = id;
		this.properties = new Properties(item);
	}

	public static class Properties
	{
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
