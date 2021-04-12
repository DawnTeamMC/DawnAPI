package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.BlockTemplate;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class MTCBlockBundle extends Bundle {
	private final Map<BlockTemplate, MCBlockBundle> map = new HashMap<>();

	/**
	 * Creates a creator bundle containing blocks built upon a base creator builder and templates in 16 colors.
	 *
	 * @param builder   The base block creator builder.
	 * @param templates The templates to use for the shapes to create.
	 */
	public MTCBlockBundle(BlockCreator.Builder builder, BlockTemplate... templates) {
		for(BlockTemplate template : templates) {
			map.put(template, put(new MCBlockBundle(builder, template)));
		}
	}

	public Block getBlock(BlockTemplate getter, DyeColor color) {
		return map.get(getter).getBlock(color);
	}
}
