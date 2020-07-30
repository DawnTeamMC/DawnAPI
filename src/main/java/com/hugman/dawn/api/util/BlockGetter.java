package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.BlockCreator;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public interface BlockGetter {
	String getSuffix();
	ItemGroup getItemGroup();
	BlockCreator.Render getRender();
	Block getBlock(AbstractBlock.Settings settings);
}
