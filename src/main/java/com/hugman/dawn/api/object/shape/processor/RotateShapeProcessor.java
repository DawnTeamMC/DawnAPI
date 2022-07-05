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

public record RotateShapeProcessor(FloatProvider xTheta, FloatProvider yTheta, FloatProvider zTheta) implements ShapeProcessor {
	public static final Codec<RotateShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT.fieldOf("x_theta").orElse(ConstantFloatProvider.create(0.0F)).forGetter((config) -> config.xTheta),
			DawnCodecs.FLOAT.fieldOf("y_theta").orElse(ConstantFloatProvider.create(0.0F)).forGetter((config) -> config.yTheta),
			DawnCodecs.FLOAT.fieldOf("z_theta").orElse(ConstantFloatProvider.create(0.0F)).forGetter((config) -> config.zTheta)
	).apply(instance, RotateShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.ROTATE;
	}

	@Override
	public Layer get(Random random) {
		return new RotateLayer(Quaternion.of(xTheta.get(random), yTheta.get(random), zTheta.get(random), true));
	}
}
