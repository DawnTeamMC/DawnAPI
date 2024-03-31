package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.DilateLayer;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record ScaleShapeProcessor(FloatProvider x, FloatProvider y, FloatProvider z) implements LayerShapeProcessor {
    public static final MapCodec<ScaleShapeProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            DawnCodecs.FLOAT_PROVIDER.fieldOf("x").orElse(ConstantFloatProvider.create(1.0F)).forGetter(ScaleShapeProcessor::x),
            DawnCodecs.FLOAT_PROVIDER.fieldOf("y").orElse(ConstantFloatProvider.create(1.0F)).forGetter(ScaleShapeProcessor::y),
            DawnCodecs.FLOAT_PROVIDER.fieldOf("z").orElse(ConstantFloatProvider.create(1.0F)).forGetter(ScaleShapeProcessor::z)
    ).apply(instance, ScaleShapeProcessor::new));

    @Override
    public ShapeProcessorType<?> getType() {
        return ShapeProcessorType.SCALE;
    }

    @Override
    public Layer get(Random random) {
        return new DilateLayer(Position.of(x.get(random), y.get(random), z.get(random)));
    }
}
