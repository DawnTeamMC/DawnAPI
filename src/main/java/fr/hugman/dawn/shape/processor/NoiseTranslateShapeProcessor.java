package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.NoiseTranslateLayer;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public record NoiseTranslateShapeProcessor(FloatProvider magnitude, Optional<Long> seed) implements ShapeProcessor {
	public static final Codec<NoiseTranslateShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.FLOAT.fieldOf("magnitude").orElse(ConstantFloatProvider.create(1.0F)).forGetter(NoiseTranslateShapeProcessor::magnitude),
			Codec.LONG.optionalFieldOf("seed").forGetter(NoiseTranslateShapeProcessor::seed)
	).apply(instance, NoiseTranslateShapeProcessor::new));

	@Override
	public ShapeProcessorType<?> getType() {
		return ShapeProcessorType.NOISE_TRANSLATE;
	}

	@Override
	public Layer get(Random random) {
		return new NoiseTranslateLayer(magnitude.get(random), seed.map(java.util.Random::new).orElseGet(java.util.Random::new));
	}
}
