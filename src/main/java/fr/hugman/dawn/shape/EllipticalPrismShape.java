package fr.hugman.dawn.shape;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record EllipticalPrismShape(FloatProvider a, FloatProvider b, FloatProvider height) implements Shape {
	public static final Codec<EllipticalPrismShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("a").forGetter(EllipticalPrismShape::a),
			DawnCodecs.FLOAT_NON_ZERO.fieldOf("b").forGetter(EllipticalPrismShape::b),
			DawnCodecs.FLOAT.fieldOf("height").forGetter(EllipticalPrismShape::height)
	).apply(instance, EllipticalPrismShape::new));

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return Shapes.ellipticalPrism(this.a.get(random), this.b.get(random), this.height.get(random));
	}

	@Override
	public ShapeType<?> getType() {
		return ShapeType.ELLIPTICAL_PRISM;
	}
}