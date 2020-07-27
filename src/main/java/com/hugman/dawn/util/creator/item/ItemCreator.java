package com.hugman.dawn.util.creator.item;

import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.pack.ModData;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ItemCreator extends Creator<Item> {
	protected final Item baseItem;
	protected final int cookTime;

	private ItemCreator(ModData modData, String name, Item baseItem, int cookTime) {
		super(modData, name);
		this.baseItem = baseItem;
		this.cookTime = cookTime;
	}

	@Override
	public Item register() {
		value = Registry.register(Registry.ITEM, modData.id(name), baseItem);
		if(cookTime != 0) {
			FuelRegistry.INSTANCE.add(value, cookTime);
		}
		return value;
	}

	public static class Builder extends Creator.Builder<Item> {
		protected final String name;
		protected final Item baseItem;
		protected int cookTime;

		/**
		 * Creates a simple item with no cook time.
		 *
		 * @param name     The name of the item.
		 * @param baseItem The item itself.
		 */
		public Builder(String name, Item baseItem) {
			super(name, baseItem);
			this.name = name;
			this.baseItem = baseItem;
			this.cookTime = 0;
		}

		public Builder setCookTime(int cookTime) {
			this.cookTime = cookTime;
			return this;
		}

		/**
		 * Builds the entry and registers the item with all its settings.
		 */
		public ItemCreator build() {
			return new ItemCreator(this.modData, this.name, this.baseItem, this.cookTime);
		}
	}
}
