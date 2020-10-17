package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ItemCreator extends Creator<Item> {
	protected final Item baseItem;
	protected final int cookTime;
	protected final float compostingChance;

	private ItemCreator(String name, Item baseItem, int cookTime, float compostingChance) {
		super(name);
		this.baseItem = baseItem;
		this.cookTime = cookTime;
		this.compostingChance = compostingChance;
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
		if(cookTime != 0) {
			FuelRegistry.INSTANCE.add(value, cookTime);
		}
		if(compostingChance != 0) {
			CompostingChanceRegistry.INSTANCE.add(value, compostingChance);
		}
	}

	public static class Builder implements CreatorBuilder {
		protected final String name;
		protected final Item baseItem;
		protected int cookTime;
		protected float compostingChance;

		/**
		 * Creates an item.
		 *
		 * @param name     The name of the item.
		 * @param baseItem The item itself.
		 */
		public Builder(String name, Item baseItem) {
			this.name = name;
			this.baseItem = baseItem;
		}

		public Builder cookTime(int cookTime) {
			this.cookTime = cookTime;
			return this;
		}

		public Builder compostingChance(float compostingChance) {
			this.compostingChance = compostingChance;
			return this;
		}

		public ItemCreator build() {
			return new ItemCreator(this.name, this.baseItem, this.cookTime, this.compostingChance);
		}
	}
}
