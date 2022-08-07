package com.hugman.dawn.api.object.shape;

import com.hugman.dawn.api.init.shape.Shape;
import com.hugman.dawn.api.init.shape.ShapeType;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record EllipseShape(FloatProvider a, FloatProvider b) implements Shape {
	public static final Codec<EllipseShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("a").forGetter(EllipseShape::a),
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("b").forGetter(EllipseShape::b)
	).apply(instance, EllipseShape::new));

	@Override
	public ShapeType<?> getType() {
		return ShapeType.ELLIPSE;
	}

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return Shapes.ellipse(this.a().get(random), this.b().get(random));
	}
}