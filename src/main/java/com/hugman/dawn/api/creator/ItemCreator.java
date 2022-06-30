package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.Objects;
import java.util.function.Function;

/**
 * A class allowing an item to be created.
 */
public class ItemCreator extends SimpleCreator<Item>
{
	protected final Builder builder;

	/**
	 * Creates an item.
	 *
	 * @param builder the builder used to create the item itself
	 */
	protected ItemCreator(Builder builder) {
		super(Registry.ITEM, builder.name, builder.itemProvider.apply(builder.settings));
		this.builder = builder;
	}

	@Override
	public void serverRegister(ModData modData, boolean isDedicated) {
		if(builder.cookTime != 0) {
			FuelRegistry.INSTANCE.add(value, builder.cookTime);
		}
		if(builder.compostingChance != 0) {
			CompostingChanceRegistry.INSTANCE.add(value, builder.compostingChance);
		}
	}

	public static class Builder
	{
		protected String name;
		protected Function<Item.Settings, ? extends Item> itemProvider;
		protected Item.Settings settings;
		protected int cookTime;
		protected float compostingChance;

		public Builder() {
		}

		public Builder(String name, Function<Item.Settings, ? extends Item> itemProvider, Item.Settings settings, int cookTime, float compostingChance) {
			this.name = name;
			this.itemProvider = itemProvider;
			this.settings = settings;
			this.cookTime = cookTime;
			this.compostingChance = compostingChance;
		}

		/**
		 * Creates an item.
		 *
		 * @param name         The name of the item.
		 * @param itemProvider The item provider.
		 */
		public Builder(String name, Function<Item.Settings, ? extends Item> itemProvider, Item.Settings settings) {
			this.name = name;
			this.itemProvider = itemProvider;
			this.settings = settings;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder item(Function<Item.Settings, ? extends Item> itemProvider) {
			this.itemProvider = itemProvider;
			return this;
		}

		public Builder settings(Item.Settings settings) {
			this.settings = settings;
			return this;
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
			Objects.requireNonNull(this.name, "Cannot build a item with no name!");
			Objects.requireNonNull(this.itemProvider, "Cannot build a item with no item provider!");
			Objects.requireNonNull(this.settings, "Cannot build a item with no item settings!");
			return new ItemCreator(copy());
		}

		public Builder copy() {
			return new Builder(this.name, this.itemProvider, this.settings, this.cookTime, this.compostingChance);
		}
	}
}
