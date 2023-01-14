package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.shapes.api.Shape;
import net.minecraft.util.math.random.Random;

public class EmptyShapeProcessor implements ShapeProcessor {
	public static final EmptyShapeProcessor INSTANCE = new EmptyShapeProcessor();
	public static final Codec<EmptyShapeProcessor> CODEC = Codec.unit(() -> INSTANCE);

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.EMPTY;
	}

	@Override
	public Shape process(Shape shape, Random random) {
		return shape;
	}
}

