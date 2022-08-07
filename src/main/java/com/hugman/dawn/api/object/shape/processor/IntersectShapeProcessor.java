package com.hugman.dawn.api.object.shape.processor;

import com.hugman.dawn.api.init.shape.ConfiguredShape;
import com.hugman.dawn.api.init.shape.ShapeProcessor;
import com.hugman.dawn.api.init.shape.ShapeProcessorType;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.IntersectLayer;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;

public record IntersectShapeProcessor(RegistryEntry<ConfiguredShape> shape) implements ShapeProcessor {
	public static final Codec<IntersectShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			ConfiguredShape.REGISTRY_CODEC.fieldOf("shape").forGetter(IntersectShapeProcessor::shape)
	).apply(instance, IntersectShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.INTERSECT;
	}

	@Override
	public Layer get(Random random) {
		return new IntersectLayer(this.shape.value().get(random));
	}
}
