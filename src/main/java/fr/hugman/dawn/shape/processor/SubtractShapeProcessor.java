package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.SubtractLayer;
import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.shape.ConfiguredShape;
import net.minecraft.util.math.random.Random;

public record SubtractShapeProcessor(ConfiguredShape shape) implements LayerShapeProcessor {
    public static final MapCodec<SubtractShapeProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            DawnRegistries.CONFIGURED_SHAPE.getEntryCodec().fieldOf("shape").forGetter(SubtractShapeProcessor::shape)
    ).apply(instance, SubtractShapeProcessor::new));

    @Override
    public ShapeProcessorType<?> getType() {
        return ShapeProcessorType.SUBTRACT;
    }

    @Override
    public Layer get(Random random) {
        return new SubtractLayer(this.shape.get(random));
    }
}
