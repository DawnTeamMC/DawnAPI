package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ColoredBlockBundle extends Bundle {
	private final Map<DyeColor, BlockCreator> map = new HashMap<>();

	/**
	 * Bundle builder that contains blocks of all 16 Minecraft colors.
	 *
	 * @param builder  the base block creator builder
	 * @param template the template to use
	 */
	@Deprecated
	public ColoredBlockBundle(BlockCreator.Builder builder, BlockCreator.Builder template) {
		this(builder.copy().from(template));
	}

	/**
	 * Bundle builder that contains blocks of all 16 Minecraft colors.
	 *
	 * @param builder  the base block creator builder
	 */
	public ColoredBlockBundle(BlockCreator.Builder builder) {
		this(color -> builder.copy().name(color.getName() + "_" + builder.getName()).build());
	}

	/**
	 * Bundle builder that contains blocks of all 16 Minecraft colors.
	 *
	 * @param function the function for creating the blocks
	 */
	public ColoredBlockBundle(Function<DyeColor, BlockCreator> function) {
		for(DyeColor color : DyeColor.values()) {
			map.put(color, put(function.apply(color)));
		}
	}

	public Block getBlock(DyeColor color) {
		return map.get(color).getValue();
	}
}
