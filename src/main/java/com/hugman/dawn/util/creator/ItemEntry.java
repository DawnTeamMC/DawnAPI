package com.hugman.dawn.util.creator;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ItemEntry extends Creator<Item> {
	protected final Item baseItem;
	protected final int cookTime;

	private ItemEntry(String name, Item baseItem, int cookTime) {
		super(name);
		this.baseItem = baseItem;
		this.cookTime = cookTime;
	}

	@Override
	public Item register(CreatorRegister creatorRegister) {
		value = Registry.register(Registry.ITEM, creatorRegister.id(name), baseItem);
		if(cookTime != 0) {
			FuelRegistry.INSTANCE.add(value, cookTime);
		}
		return value;
	}

	public static class Builder {
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
		public ItemEntry build() {
			return new ItemEntry(this.name, this.baseItem, this.cookTime);
		}
	}
}
