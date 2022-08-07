package com.hugman.dawn.api.object.shape.processor;

import com.hugman.dawn.api.init.shape.ConfiguredShape;
import com.hugman.dawn.api.init.shape.ShapeProcessor;
import com.hugman.dawn.api.init.shape.ShapeProcessorType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;

public record AddShapeProcessor(RegistryEntry<ConfiguredShape> shape) implements ShapeProcessor {
	public static final Codec<AddShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			ConfiguredShape.REGISTRY_CODEC.fieldOf("shape").forGetter(AddShapeProcessor::shape)
	).apply(instance, AddShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.ADD;
	}

	@Override
	public Layer get(Random random) {
		return new AddLayer(this.shape.value().get(random));
	}
}
