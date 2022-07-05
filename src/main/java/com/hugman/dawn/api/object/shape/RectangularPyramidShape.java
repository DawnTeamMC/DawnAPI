package com.hugman.dawn.api.object.shape;

import com.hugman.dawn.api.init.shape.Shape;
import com.hugman.dawn.api.init.shape.ShapeType;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record RectangularPyramidShape(FloatProvider width, FloatProvider height, FloatProvider depth) implements Shape {
	public static final Codec<RectangularPyramidShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT.fieldOf("width").forGetter(RectangularPyramidShape::width),
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("height").forGetter(RectangularPyramidShape::height),
			DawnCodecs.FLOAT.fieldOf("depth").forGetter(RectangularPyramidShape::depth)
	).apply(instance, RectangularPyramidShape::new));

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return Shapes.rectangularPyramid(this.width.get(random), this.height.get(random), this.depth.get(random));
	}

	@Override
	public ShapeType<?> getType() {
		return ShapeType.RECTANGULAR_PYRAMID;
	}
}