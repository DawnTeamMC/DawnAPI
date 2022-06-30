package com.hugman.dawn.api.creator;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a painting motive to be created.
 */
public class PaintingCreator extends SimpleCreator<PaintingVariant>
{

	/**
	 * Creates a painting motive.
	 *
	 * @param name   the name of the painting motive
	 * @param width  the width, as a multiple of 16
	 * @param height the height, as a multiple of 16
	 */
	public PaintingCreator(String name, int width, int height) {
		super(Registry.PAINTING_VARIANT, name, new PaintingVariant(width, height));
	}

	/**
	 * Creates a painting motive, with the same width and height.
	 *
	 * @param name the name of the painting motive
	 * @param size the size, as a multiple of 16
	 */
	public PaintingCreator(String name, int size) {
		this(name, size, size);
	}
}
