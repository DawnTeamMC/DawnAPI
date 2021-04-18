package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.BlockCreator;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

import java.util.function.Function;

public class BlockTemplate {
	private final String suffix;
	private final ItemGroup itemGroup;
	private final BlockCreator.Render render;
	private final Function<AbstractBlock.Settings, ? extends Block> blockProvider;

	public BlockTemplate(Function<AbstractBlock.Settings, ? extends Block> blockProvider, String suffix, ItemGroup itemGroup, BlockCreator.Render render) {
		this.suffix = suffix;
		this.itemGroup = itemGroup;
		this.render = render;
		this.blockProvider = blockProvider;
	}

	public BlockTemplate(Function<AbstractBlock.Settings, ? extends Block> blockProvider, String suffix, ItemGroup itemGroup) {
		this(blockProvider, suffix, itemGroup, null);
	}

	public String getSuffix() {
		return suffix;
	}

	public ItemGroup getItemGroup() {
		return itemGroup;
	}

	public BlockCreator.Render getRender() {
		return render;
	}

	public Function<AbstractBlock.Settings, ? extends Block> getBlockProvider() {
		return blockProvider;
	}
}
