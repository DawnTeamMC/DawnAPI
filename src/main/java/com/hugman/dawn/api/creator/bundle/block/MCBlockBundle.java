package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.BlockTemplate;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;

public class MCBlockBundle extends Bundle {
	private final Map<DyeColor, BlockCreator> map = new HashMap<>();

	/**
	 * Creates a creator bundle containing blocks of 16 different colors.
	 *
	 * @param builder  The base block creator builder.
	 * @param template The template to use.
	 */
	public MCBlockBundle(BlockCreator.Builder builder, BlockTemplate template) {
		for(DyeColor color : DyeColor.values()) {
			map.put(color, put(builder.copy().applyTemplate(template).name(color.getName() + "_" + builder.getName()).build()));
		}
	}

	public Block getBlock(DyeColor color) {
		return map.get(color).getValue();
	}
}
