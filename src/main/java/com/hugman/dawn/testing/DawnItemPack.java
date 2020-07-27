package com.hugman.dawn.testing;

import com.hugman.dawn.util.creator.item.ItemCreator;
import com.hugman.dawn.util.pack.ModData;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;

public class DawnItemPack extends ModdedPack<Item> {

	public DawnItemPack(ModData modData) {
		super(modData);
	}

	public final Item test_item = add(new ItemCreator.Builder("test_item", new Item(new Settings().group(ItemGroup.BREWING))));
}
