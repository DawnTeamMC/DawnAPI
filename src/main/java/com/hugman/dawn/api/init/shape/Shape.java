package com.hugman.dawn.api.init.shape;

import com.hugman.dawn.api.DawnRegistries;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.random.Random;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Shape {
	public static final Codec<Shape> TYPE_CODEC = DawnRegistries.SHAPE_TYPE.getCodec().dispatch(Shape::getType, ShapeType::codec);

	protected final List<ShapeProcessor> processors;

	protected static <S extends Shape> RecordCodecBuilder<S, List<ShapeProcessor>> processorsCodec() {
		return ShapeProcessor.CODEC.listOf().optionalFieldOf("processors").xmap(o -> o.orElse(Collections.emptyList()), Optional::ofNullable).forGetter((shape) -> shape.processors);
	}

	public Shape(List<ShapeProcessor> processors) {
		this.processors = processors;
	}

	protected abstract ShapeType<?> getType();

	protected abstract com.terraformersmc.terraform.shapes.api.Shape construct(Random random);

	public com.terraformersmc.terraform.shapes.api.Shape create(Random random) {
		var shape = this.construct(random);
		for(var processor : this.processors) {
			shape = shape.applyLayer(processor.create(random));
		}
		return shape;
	}
}
