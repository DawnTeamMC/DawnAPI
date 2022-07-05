package com.hugman.dawn.api.object.shape;

import com.hugman.dawn.api.init.shape.Shape;
import com.hugman.dawn.api.init.shape.ShapeType;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record EllipsoidShape(FloatProvider a, FloatProvider b, FloatProvider c) implements Shape {
	public static final Codec<EllipsoidShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("a").forGetter(EllipsoidShape::a),
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("b").forGetter(EllipsoidShape::b),
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("c").forGetter(EllipsoidShape::c)
	).apply(instance, EllipsoidShape::new));

	@Override
	public ShapeType<?> getType() {
		return ShapeType.ELLIPSOID;
	}

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return Shapes.ellipsoid(this.a.get(random), this.b.get(random), this.c.get(random));
	}
}