package com.hugman.dawn.api.creator;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ItemCreator extends Creator<Item> {
	protected final Item baseItem;
	protected final int cookTime;
	protected final float compostingChance;
	protected final Item copiedItem;

	private ItemCreator(ModData modData, String name, Item baseItem, int cookTime, float compostingChance, Item copiedItem) {
		super(modData, name);
		this.baseItem = baseItem;
		this.cookTime = cookTime;
		this.compostingChance = compostingChance;
		this.copiedItem = copiedItem;
	}

	@Override
	public Item register() {
		value = Registry.register(Registry.ITEM, modData.id(name), baseItem);
		if(cookTime != 0) {
			FuelRegistry.INSTANCE.add(value, cookTime);
		}
		return value;
	}

	@Override
	public void serverRegister(boolean isDedicated) {
		int cookTimeF = cookTime;
		float compostingChanceF = compostingChance;
		if(cookTime == 0 && copiedItem != null) {
			// TODO
			// cookTimeF = FuelRegistry.INSTANCE.get(copiedBlock);
		}
		if(cookTime != 0) {
			FuelRegistry.INSTANCE.add(value, cookTimeF);
		}
		if(compostingChance == 0 && copiedItem != null) {
			compostingChanceF = CompostingChanceRegistry.INSTANCE.get(copiedItem);
		}
		if(compostingChance != 0) {
			CompostingChanceRegistry.INSTANCE.add(value, compostingChanceF);
		}
	}

	public static class Builder extends Creator.Builder<Item> {
		protected final String name;
		protected final Item baseItem;
		protected int cookTime;
		protected float compostingChance;
		protected Item copiedItem;

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

		public Builder setCompostingChance(float compostingChance) {
			this.compostingChance = compostingChance;
			return this;
		}

		/**
		 * Copies some properties from an item. (cook time)
		 */
		public Builder copy(Item copiedItem) {
			this.copiedItem = copiedItem;
			return this;
		}

		/**
		 * Builds the entry and registers the item with all its settings.
		 */
		public ItemCreator build(ModData modData) {
			return new ItemCreator(modData, this.name, this.baseItem, this.cookTime, this.compostingChance, this.copiedItem);
		}
	}
}
