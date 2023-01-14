package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.IntersectLayer;
import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.shape.ConfiguredShape;
import net.minecraft.util.math.random.Random;

public record IntersectShapeProcessor(ConfiguredShape shape) implements LayerShapeProcessor {
	public static final Codec<IntersectShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnRegistries.CONFIGURED_SHAPE.getEntryCodec().fieldOf("shape").forGetter(IntersectShapeProcessor::shape)
	).apply(instance, IntersectShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.INTERSECT;
	}

	@Override
	public Layer get(Random random) {
		return new IntersectLayer(this.shape.get(random));
	}
}
