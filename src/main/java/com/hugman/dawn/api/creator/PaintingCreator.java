package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.registry.Registry;

public class PaintingCreator extends Creator<PaintingMotive> {
	protected final int width;
	protected final int height;

	private PaintingCreator(String name, int width, int height) {
		super(name);
		this.width = width;
		this.height = height;
	}

	@Override
	public PaintingMotive register(ModData modData) {
		value = Registry.register(Registry.PAINTING_MOTIVE, modData.id(name), new PaintingMotive(width, height));
		return value;
	}

	public static class Builder implements CreatorBuilder {
		protected final String name;
		protected final int width;
		protected final int height;

		/**
		 * Creates a square painting.
		 *
		 * @param name   The name of the paining.
		 * @param length The length of the square painting. (in blocks)
		 */
		public Builder(String name, int length) {
			this(name, length, length);
		}

		/**
		 * Creates a rectangle painting.
		 *
		 * @param name   The name of the paining.
		 * @param width  The width of the square painting. (in blocks)
		 * @param height The height of the square painting. (in blocks)
		 */
		public Builder(String name, int width, int height) {
			this.name = name;
			this.width = width * 16;
			this.height = height * 16;
		}

		public PaintingCreator build() {
			return new PaintingCreator(this.name, this.width, this.height);
		}
	}
}
