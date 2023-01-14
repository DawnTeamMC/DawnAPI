package fr.hugman.dawn.shape;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record EllipseShape(FloatProvider a, FloatProvider b) implements Shape {
	public static final Codec<EllipseShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("a").forGetter(EllipseShape::a),
			DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("b").forGetter(EllipseShape::b)
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