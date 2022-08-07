package com.hugman.dawn.api.object.shape.processor;

import com.hugman.dawn.api.init.shape.ShapeProcessor;
import com.hugman.dawn.api.init.shape.ShapeProcessorType;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record TranslateShapeProcessor(FloatProvider x, FloatProvider y, FloatProvider z) implements ShapeProcessor {
	public static final Codec<TranslateShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT.fieldOf("x").orElse(ConstantFloatProvider.create(0.0F)).forGetter(TranslateShapeProcessor::x),
			DawnCodecs.FLOAT.fieldOf("y").orElse(ConstantFloatProvider.create(0.0F)).forGetter(TranslateShapeProcessor::y),
			DawnCodecs.FLOAT.fieldOf("z").orElse(ConstantFloatProvider.create(0.0F)).forGetter(TranslateShapeProcessor::z)
	).apply(instance, TranslateShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.TRANSLATE;
	}

	@Override
	public Layer get(Random random) {
		return new TranslateLayer(Position.of(x.get(random), y.get(random), z.get(random)));
	}
}
