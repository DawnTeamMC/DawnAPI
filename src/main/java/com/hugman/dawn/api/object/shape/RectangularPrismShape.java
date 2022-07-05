package com.hugman.dawn.api.object.shape;

import com.hugman.dawn.api.init.shape.Shape;
import com.hugman.dawn.api.init.shape.ShapeType;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record RectangularPrismShape(FloatProvider width, FloatProvider height, FloatProvider depth) implements Shape {
	public static final Codec<RectangularPrismShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT.fieldOf("width").forGetter(RectangularPrismShape::width),
			DawnCodecs.FLOAT.fieldOf("height").forGetter(RectangularPrismShape::height),
			DawnCodecs.FLOAT.fieldOf("depth").forGetter(RectangularPrismShape::depth)
	).apply(instance, RectangularPrismShape::new));

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return Shapes.rectanglarPrism(this.width.get(random), this.height.get(random), this.depth.get(random));
	}

	@Override
	public ShapeType<?> getType() {
		return ShapeType.RECTANGULAR_PRISM;
	}
}