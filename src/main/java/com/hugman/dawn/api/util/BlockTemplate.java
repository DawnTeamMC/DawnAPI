package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.BlockCreator;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

import java.util.function.Function;

public interface BlockTemplate {
	String getSuffix();

	ItemGroup getItemGroup();

	BlockCreator.Render getRender();

	Function<AbstractBlock.Settings, ? extends Block> getBlockProvider();
}
