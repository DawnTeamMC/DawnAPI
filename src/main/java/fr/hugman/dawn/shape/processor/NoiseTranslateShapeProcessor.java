package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.NoiseTranslateLayer;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record NoiseTranslateShapeProcessor(FloatProvider magnitude) implements LayerShapeProcessor {
    public static final MapCodec<NoiseTranslateShapeProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            DawnCodecs.FLOAT_PROVIDER.fieldOf("magnitude").orElse(ConstantFloatProvider.create(1.0F)).forGetter(NoiseTranslateShapeProcessor::magnitude)
    ).apply(instance, NoiseTranslateShapeProcessor::new));

    @Override
    public ShapeProcessorType<?> getType() {
        return ShapeProcessorType.NOISE_TRANSLATE;
    }

    @Override
    public Layer get(Random random) {
        return new NoiseTranslateLayer(magnitude.get(random), random);
    }
}
