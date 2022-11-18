package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.ExcludeLayer;
import fr.hugman.dawn.shape.ConfiguredShape;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;

public record ExcludeShapeProcessor(RegistryEntry<ConfiguredShape> shape) implements ShapeProcessor {
	public static final Codec<ExcludeShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			ConfiguredShape.REGISTRY_CODEC.fieldOf("shape").forGetter(ExcludeShapeProcessor::shape)
	).apply(instance, ExcludeShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.EXCLUDE;
	}

	@Override
	public Layer get(Random random) {
		return new ExcludeLayer(shape.value().get(random));
	}
}
