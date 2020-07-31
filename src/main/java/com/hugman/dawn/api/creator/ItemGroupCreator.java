package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.List;

public class ItemGroupCreator extends Creator<ItemGroup> {
	protected final Item baseItem;
	protected final List<ItemStack> stacks;

	private ItemGroupCreator(String name, Item baseItem, List<ItemStack> stacks) {
		super(name);
		this.baseItem = baseItem;
		this.stacks = stacks;
	}

	@Override
	public ItemGroup register(ModData modData) {
		FabricItemGroupBuilder builder = FabricItemGroupBuilder.create(modData.id(name));
		builder.icon(() -> new ItemStack(baseItem));
		if(!stacks.isEmpty()) builder.appendItems(itemStacks -> itemStacks.addAll(stacks));
		value = builder.build();
		return value;
	}

	public static class Builder implements CreatorBuilder {
		protected final String name;
		protected final Item baseItem;
		protected final List<ItemStack> stacks = new ArrayList<>();

		/**
		 * Creates an item group.
		 *
		 * @param name     The name of the item group.
		 * @param baseItem The item to display.
		 */
		public Builder(String name, Item baseItem) {
			this.name = name;
			this.baseItem = baseItem;
		}

		public Builder withItems(ItemConvertible... items) {
			for(ItemConvertible item : items) {
				this.stacks.add(new ItemStack(item));
			}
			return this;
		}

		public ItemGroupCreator build() {
			return new ItemGroupCreator(this.name, this.baseItem, this.stacks);
		}
	}
}
