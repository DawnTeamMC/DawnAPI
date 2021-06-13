package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import com.hugman.dawn.api.util.BlockTemplate;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MTCBlockBundle extends Bundle {
	private final Map<Pair<BlockTemplate, DyeColor>, BlockCreator> map = new HashMap<>();

	/**
	 * Creates a creator bundle containing blocks built upon a base creator builder and templates in 16 colors.
	 *
	 * @param builder   The base block creator builder
	 * @param templates The templates to use for the shapes to create
	 */
	public MTCBlockBundle(BlockCreator.Builder builder, BlockTemplate... templates) {
		this((template, color) -> builder.copy().name(color.getName() + "_" + builder.getName()).applyTemplate(template).build(), templates);
	}

	/**
	 * Creates a creator bundle containing blocks built upon a base creator builder and templates in 16 colors.
	 *
	 * @param function  The function for creating the blocks
	 * @param templates The templates to use for the shapes to create
	 */
	public MTCBlockBundle(BiFunction<BlockTemplate, DyeColor, BlockCreator> function, BlockTemplate... templates) {
		for(BlockTemplate template : templates) {
			for(DyeColor color : DyeColor.values()) {
				BlockCreator creator = put(function.apply(template, color));
				map.put(Pair.of(template, color), creator);
			}
		}
	}

	public Block getBlock(BlockTemplate template, DyeColor color) {
		return map.get(Pair.of(template, color)).getValue();
	}
}
