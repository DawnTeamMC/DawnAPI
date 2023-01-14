package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer;
import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.shape.ConfiguredShape;
import net.minecraft.util.math.random.Random;

public record AddShapeProcessor(ConfiguredShape shape) implements LayerShapeProcessor {
	public static final Codec<AddShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnRegistries.CONFIGURED_SHAPE.getEntryCodec().fieldOf("shape").forGetter(AddShapeProcessor::shape)
	).apply(instance, AddShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.ADD;
	}

	@Override
	public Layer get(Random random) {
		return new AddLayer(this.shape.get(random));
	}
}
