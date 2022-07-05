package com.hugman.dawn.api.object.shape.processor;

import com.hugman.dawn.api.init.shape.ConfiguredShape;
import com.hugman.dawn.api.init.shape.ShapeProcessor;
import com.hugman.dawn.api.init.shape.ShapeProcessorType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.SubtractLayer;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;

public record SubtractShapeProcessor(RegistryEntry<ConfiguredShape> shape) implements ShapeProcessor {
	public static final Codec<SubtractShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			ConfiguredShape.REGISTRY_CODEC.fieldOf("shape").forGetter(SubtractShapeProcessor::shape)
	).apply(instance, SubtractShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.SUBTRACT;
	}

	@Override
	public Layer get(Random random) {
		return new SubtractLayer(this.shape.value().get(random));
	}
}
