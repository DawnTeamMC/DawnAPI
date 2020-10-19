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
	public static ItemGroup CREATIVE_TOOLS;

	public static void init() {
		FabricItemGroupBuilder CREATIVE_TOOLS_BUILDER = FabricItemGroupBuilder.create(Dawn.MOD_DATA.id("creative_tools")).icon(() -> new ItemStack(Blocks.COMMAND_BLOCK)).appendItems(list -> {
			list.add(new ItemStack(Blocks.COMMAND_BLOCK));
			list.add(new ItemStack(Blocks.CHAIN_COMMAND_BLOCK));
			list.add(new ItemStack(Blocks.REPEATING_COMMAND_BLOCK));
			list.add(new ItemStack(Items.COMMAND_BLOCK_MINECART));
			list.add(new ItemStack(Items.STRUCTURE_BLOCK));
			list.add(new ItemStack(Items.STRUCTURE_VOID));
			list.add(new ItemStack(Items.JIGSAW));
			list.add(new ItemStack(Items.DEBUG_STICK));
			list.add(new ItemStack(Blocks.BARRIER));
			list.add(new ItemStack(Blocks.SPAWNER));
			list.add(new ItemStack(Blocks.DRAGON_EGG));
		});
		if(Dawn.CONFIG.features.creativeToolsTab) {
			CREATIVE_TOOLS = CREATIVE_TOOLS_BUILDER.build();
		}
	}
}
