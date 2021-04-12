package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.BlockTemplate;
import net.minecraft.block.Block;

import java.util.HashMap;
import java.util.Map;

public class MTBlockBundle extends Bundle {
	private final Map<BlockTemplate, BlockCreator> map = new HashMap<>();

	/**
	 * Creates a creator bundle containing blocks built upon a base creator builder and templates.
	 *
	 * @param builder   The base block creator builder.
	 * @param templates The templates to use.
	 */
	public MTBlockBundle(BlockCreator.Builder builder, BlockTemplate... templates) {
		for(BlockTemplate template : templates) {
			map.put(template, put(builder.copy().applyTemplate(template).build()));
		}
	}

	public Block getBlock(BlockTemplate getter) {
		return map.get(getter).getValue();
	}
}
