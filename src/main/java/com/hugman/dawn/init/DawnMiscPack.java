package com.hugman.dawn.init;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.ItemGroupCreator;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.util.CreatorBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;

public class DawnMiscPack extends Pack {
	protected static <V, B extends CreatorBuilder> V register(B creatorBuilder) {
		return add(creatorBuilder, Dawn.MOD_DATA);
	}

	protected static <P extends Pack, B extends PackBuilder> P register(B packBuilder) {
		return add(packBuilder, Dawn.MOD_DATA);
	}

	public static final ItemGroup CREATIVE_TOOLS = register(new ItemGroupCreator.Builder("creative_tools", Items.COMMAND_BLOCK).withItems(
			Blocks.COMMAND_BLOCK,
			Blocks.CHAIN_COMMAND_BLOCK,
			Blocks.REPEATING_COMMAND_BLOCK,
			Items.COMMAND_BLOCK_MINECART,
			Items.STRUCTURE_BLOCK,
			Items.STRUCTURE_VOID,
			Items.JIGSAW,
			Items.DEBUG_STICK,
			Blocks.BARRIER,
			Blocks.SPAWNER,
			Blocks.DRAGON_EGG));
}
