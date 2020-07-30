package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.SimpleBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ItemCreator extends Creator<Item> {
	protected final Item baseItem;
	protected final int cookTime;
	protected final float compostingChance;
	protected final Item copiedItem;

	private ItemCreator(String name, Item baseItem, int cookTime, float compostingChance, Item copiedItem) {
		super(name);
		this.baseItem = baseItem;
		this.cookTime = cookTime;
		this.compostingChance = compostingChance;
		this.copiedItem = copiedItem;
	}

	@Override
	public Item register(ModData modData) {
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
			// cookTimeF = FuelRegistry.INSTANCE.get(copiedItem);
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

	public static class Builder implements SimpleBuilder {
		protected final String name;
		protected final Item baseItem;
		protected int cookTime;
		protected float compostingChance;
		protected Item copiedItem;

		/**
		 * Creates an item.
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

		public ItemCreator build() {
			return new ItemCreator(this.name, this.baseItem, this.cookTime, this.compostingChance, this.copiedItem);
		}
	}
}
