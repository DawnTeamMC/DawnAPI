package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Shape;
import net.minecraft.util.math.random.Random;

import java.util.Collections;
import java.util.List;

public record ListShapeProcessor(List<ShapeProcessor> processors) implements ShapeProcessor {
    public static final MapCodec<ListShapeProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            ShapeProcessor.CODEC.listOf().optionalFieldOf("processors", Collections.emptyList()).forGetter(ListShapeProcessor::processors)
    ).apply(instance, ListShapeProcessor::new));

    @Override
    public ShapeProcessorType<?> getType() {
        return ShapeProcessorType.LIST;
    }

    @Override
    public Shape process(Shape shape, Random random) {
        for (ShapeProcessor processor : processors) {
            shape = processor.process(shape, random);
        }
        return shape;
    }
}
