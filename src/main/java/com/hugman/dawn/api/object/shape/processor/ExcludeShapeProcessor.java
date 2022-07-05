package com.hugman.dawn.api.object.shape.processor;

import com.hugman.dawn.api.init.shape.ShapeProcessor;
import com.hugman.dawn.api.init.shape.ShapeProcessorType;
import com.hugman.dawn.api.object.shape.ConfiguredShape;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.ExcludeLayer;
import net.minecraft.util.math.random.Random;

public record ExcludeShapeProcessor(ConfiguredShape shape) implements ShapeProcessor {
	public static final Codec<ExcludeShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.CONFIGURED_SHAPE.fieldOf("shape").forGetter(ExcludeShapeProcessor::shape)
	).apply(instance, ExcludeShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.EXCLUDE;
	}

	@Override
	public Layer get(Random random) {
		return new ExcludeLayer(shape.get(random));
	}
}
