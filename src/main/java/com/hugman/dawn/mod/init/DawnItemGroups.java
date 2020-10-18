package com.hugman.dawn.mod.init;

import com.hugman.dawn.Dawn;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class DawnItemGroups {
	protected static final List<ItemConvertible> CREATIVE_ITEMS = new ArrayList<>();

	public static void init() {
		if(Dawn.CONFIG.features.creativeToolsTab) {
			CREATIVE_ITEMS.add(Blocks.COMMAND_BLOCK);
			CREATIVE_ITEMS.add(Blocks.CHAIN_COMMAND_BLOCK);
			CREATIVE_ITEMS.add(Blocks.REPEATING_COMMAND_BLOCK);
			CREATIVE_ITEMS.add(Items.COMMAND_BLOCK_MINECART);
			CREATIVE_ITEMS.add(Items.STRUCTURE_BLOCK);
			CREATIVE_ITEMS.add(Items.STRUCTURE_VOID);
			CREATIVE_ITEMS.add(Items.JIGSAW);
			CREATIVE_ITEMS.add(Items.DEBUG_STICK);
			CREATIVE_ITEMS.add(Blocks.BARRIER);
			CREATIVE_ITEMS.add(Blocks.SPAWNER);
			CREATIVE_ITEMS.add(Blocks.DRAGON_EGG);
			CREATIVE_TOOLS.build();
		}
	}

	public static final FabricItemGroupBuilder CREATIVE_TOOLS = FabricItemGroupBuilder.create(Dawn.MOD_DATA.id("creative_tools")).icon(() -> new ItemStack(Blocks.COMMAND_BLOCK)).appendItems(itemStacks -> {
		for(ItemConvertible item : CREATIVE_ITEMS) {
			itemStacks.add(new ItemStack(item));
		}
	});
}
