package fr.hugman.dawn.shape;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.shapes.api.Position;
import net.minecraft.util.math.random.Random;

public class EmptyShape implements Shape {
	public static final EmptyShape INSTANCE = new EmptyShape();

	public static final Codec<EmptyShape> CODEC = Codec.unit(() -> INSTANCE);

	@Override
	public ShapeType<?> getType() {
		return ShapeType.EMPTY;
	}

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return com.terraformersmc.terraform.shapes.api.Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0));
	}
}