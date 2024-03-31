package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Shape;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;

public record RepeatShapeProcessor(IntProvider count, ShapeProcessor processor) implements ShapeProcessor {
    public static final MapCodec<RepeatShapeProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            DawnCodecs.POSITIVE_INT_PROVIDER.fieldOf("count").forGetter(RepeatShapeProcessor::count),
            ShapeProcessor.MAP_CODEC.forGetter(RepeatShapeProcessor::processor)
    ).apply(instance, RepeatShapeProcessor::new));

    @Override
    public ShapeProcessorType<?> getType() {
        return ShapeProcessorType.REPEAT;
    }

    @Override
    public Shape process(Shape shape, Random random) {
        for (int i = 0; i < count.get(random); i++) {
            shape = processor.process(shape, random);
        }
        return shape;
    }
}
