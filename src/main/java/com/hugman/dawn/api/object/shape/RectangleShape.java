package com.hugman.dawn.api.object.shape;

import com.hugman.dawn.api.init.shape.Shape;
import com.hugman.dawn.api.init.shape.ShapeType;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record RectangleShape(FloatProvider width, FloatProvider height) implements Shape {
	public static final Codec<RectangleShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("width").forGetter(RectangleShape::width),
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("height").forGetter(RectangleShape::height)
	).apply(instance, RectangleShape::new));

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return Shapes.rectangle(this.width.get(random), this.height.get(random));
	}

	@Override
	public ShapeType<?> getType() {
		return ShapeType.RECTANGLE;
	}
}