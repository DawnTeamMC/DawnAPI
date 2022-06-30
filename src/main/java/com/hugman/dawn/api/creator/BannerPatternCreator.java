package com.hugman.dawn.api.creator;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a banner pattern to be created.
 */
public class BannerPatternCreator extends SimpleCreator<BannerPattern>
{
	/**
	 * Creates a banner pattern.
	 *
	 * @param name the name of the pattern
	 * @param id   the id of the pattern (ideally a maximum of 3 characters)
	 */
	public BannerPatternCreator(String name, String id) {
		super(Registry.BANNER_PATTERN, name, new BannerPattern(id));
	}
}
