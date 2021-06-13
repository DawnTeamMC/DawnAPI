package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.BlockTemplate;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class MCBlockBundle extends Bundle {
	private final Map<DyeColor, BlockCreator> map = new HashMap<>();

	/**
	 * Creates a creator bundle containing blocks of 16 different colors.
	 *
	 * @param builder  The base block creator builder.
	 * @param template The template to use.
	 */
	public MCBlockBundle(BlockCreator.Builder builder, BlockTemplate template) {
		this(color -> builder.copy().name(color.getName() + "_" + builder.getName()).applyTemplate(template).build());
	}

	/**
	 * Creates a creator bundle containing blocks of 16 different colors.
	 *
	 * @param function The function for creating the blocks
	 */
	public MCBlockBundle(Function<DyeColor, BlockCreator> function) {
		for(DyeColor color : DyeColor.values()) {
			map.put(color, put(function.apply(color)));
		}
	}

	public Block getBlock(DyeColor color) {
		return map.get(color).getValue();
	}
}
