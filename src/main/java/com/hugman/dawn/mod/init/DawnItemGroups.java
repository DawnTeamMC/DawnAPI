package com.hugman.dawn.mod.init;

import com.hugman.dawn.Dawn;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.resource.featuretoggle.FeatureSet;

public class DawnItemGroups {
	public static ItemGroup CREATIVE_TOOLS;

	public static void init() {
		FabricItemGroup CREATIVE_TOOLS_BUILDER = new FabricItemGroup(Dawn.MOD_DATA.id("creative_tools")) {
			@Override
			public ItemStack createIcon() {
				return new ItemStack(Blocks.COMMAND_BLOCK);
			}

			@Override
			protected void addItems(FeatureSet enabledFeatures, Entries entries, boolean hasPermissions) {
				entries.add(new ItemStack(Blocks.COMMAND_BLOCK));
				entries.add(new ItemStack(Blocks.CHAIN_COMMAND_BLOCK));
				entries.add(new ItemStack(Blocks.REPEATING_COMMAND_BLOCK));
				entries.add(new ItemStack(Items.COMMAND_BLOCK_MINECART));
				entries.add(new ItemStack(Blocks.LIGHT));
				entries.add(new ItemStack(Items.STRUCTURE_BLOCK));
				entries.add(new ItemStack(Items.STRUCTURE_VOID));
				entries.add(new ItemStack(Items.JIGSAW));
				entries.add(new ItemStack(Items.DEBUG_STICK));
				entries.add(new ItemStack(Blocks.BARRIER));
				entries.add(new ItemStack(Blocks.SPAWNER));
				entries.add(new ItemStack(Blocks.DRAGON_EGG));
			}
		};
		if(Dawn.CONFIG.features.creativeToolsTab) {
			CREATIVE_TOOLS = CREATIVE_TOOLS_BUILDER;
		};
	}
}
