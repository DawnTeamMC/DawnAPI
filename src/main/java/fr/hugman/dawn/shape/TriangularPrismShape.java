package fr.hugman.dawn.shape;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record TriangularPrismShape(FloatProvider width, FloatProvider height, FloatProvider depth) implements Shape {
	public static final Codec<TriangularPrismShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT_PROVIDER.fieldOf("width").forGetter(TriangularPrismShape::width),
			DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("height").forGetter(TriangularPrismShape::height),
			DawnCodecs.FLOAT_PROVIDER.fieldOf("depth").forGetter(TriangularPrismShape::depth)
	).apply(instance, TriangularPrismShape::new));

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return Shapes.triangularPrism(this.width.get(random), this.height.get(random), this.depth.get(random));
	}

	@Override
	public ShapeType<?> getType() {
		return ShapeType.TRIANGULAR_PRISM;
	}
}