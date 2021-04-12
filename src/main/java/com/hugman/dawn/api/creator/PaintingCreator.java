package com.hugman.dawn.api.creator;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.registry.Registry;

public class PaintingCreator extends SimpleCreator<PaintingMotive> {
	public PaintingCreator(String name, int width, int height) {
		super(Registry.PAINTING_MOTIVE, name, new PaintingMotive(width, height));
	}

	public PaintingCreator(String name, int size) {
		this(name, size, size);
	}
}
