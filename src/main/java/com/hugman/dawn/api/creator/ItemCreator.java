package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ItemCreator extends Creator<Item> {
	protected final int cookTime;
	protected final float compostingChance;

	private ItemCreator(String name, Item item, ModData modData, int cookTime, float compostingChance) {
		super(modData, name, item);
		this.cookTime = cookTime;
		this.compostingChance = compostingChance;
	}

	@Override
	public void register() {
		Registry.register(Registry.ITEM, modData.id(name), value);
		if(cookTime != 0) {
			FuelRegistry.INSTANCE.add(value, cookTime);
		}
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

	public static class Builder implements Creator.Builder<Item> {
		protected final String name;
		protected final Item item;
		protected int cookTime;
		protected float compostingChance;

		/**
		 * Creates an item.
		 *
		 * @param name The name of the item.
		 * @param item The item itself.
		 */
		public Builder(String name, Item item) {
			this.name = name;
			this.item = item;
		}

		public Builder cookTime(int cookTime) {
			this.cookTime = cookTime;
			return this;
		}

		public Builder compostingChance(float compostingChance) {
			this.compostingChance = compostingChance;
			return this;
		}

		public ItemCreator build(ModData modData) {
			return new ItemCreator(this.name, this.item, modData, this.cookTime, this.compostingChance);
		}
	}
}
