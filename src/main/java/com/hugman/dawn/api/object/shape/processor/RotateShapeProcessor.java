package com.hugman.dawn.api.object.shape.processor;

import com.hugman.dawn.api.init.shape.ShapeProcessor;
import com.hugman.dawn.api.init.shape.ShapeProcessorType;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Quaternion;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.RotateLayer;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record RotateShapeProcessor(FloatProvider x, FloatProvider y, FloatProvider z, boolean degrees) implements ShapeProcessor {
	public static final Codec<RotateShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT.fieldOf("x").orElse(ConstantFloatProvider.create(0.0F)).forGetter(RotateShapeProcessor::x),
			DawnCodecs.FLOAT.fieldOf("y").orElse(ConstantFloatProvider.create(0.0F)).forGetter(RotateShapeProcessor::y),
			DawnCodecs.FLOAT.fieldOf("z").orElse(ConstantFloatProvider.create(0.0F)).forGetter(RotateShapeProcessor::z),
			Codec.BOOL.fieldOf("degrees").orElse(true).forGetter(RotateShapeProcessor::degrees)
	).apply(instance, RotateShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.ROTATE;
	}

	@Override
	public Layer get(Random random) {
		return new RotateLayer(Quaternion.of(this.x.get(random), this.y.get(random), this.z.get(random), this.degrees));
	}
}
