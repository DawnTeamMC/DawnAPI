package fr.hugman.dawn.shape;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record EllipticalPyramidShape(FloatProvider a, FloatProvider b, FloatProvider height) implements Shape {
	public static final Codec<EllipticalPyramidShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("a").forGetter(EllipticalPyramidShape::a),
			DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("b").forGetter(EllipticalPyramidShape::b),
			DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("height").forGetter(EllipticalPyramidShape::height)
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