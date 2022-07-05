package com.hugman.dawn.api.object.shape;

import com.hugman.dawn.api.init.shape.Shape;
import com.hugman.dawn.api.init.shape.ShapeType;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record EllipticalPyramidShape(FloatProvider a, FloatProvider b, FloatProvider height) implements Shape {
	public static final Codec<EllipticalPyramidShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("a").forGetter(EllipticalPyramidShape::a),
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("b").forGetter(EllipticalPyramidShape::b),
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("height").forGetter(EllipticalPyramidShape::height)
	).apply(instance, EllipticalPyramidShape::new));

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return Shapes.ellipticalPyramid(a.get(random), b.get(random), height.get(random));
	}

	@Override
	public ShapeType<?> getType() {
		return ShapeType.ELLIPTICAL_PYRAMID;
	}
}