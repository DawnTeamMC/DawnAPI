package fr.hugman.dawn.shape;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.dawn.command.ShapeCommand;
import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.shape.processor.ShapeProcessor;
import fr.hugman.dawn.world.gen.feature.ShapeFeature;
import net.minecraft.util.math.random.Random;

/**
 * A configured shape represents a {@link Shape shape} with its {@link ShapeProcessor processors}.
 * <p>They can be used in commands, world gen features.
 * <p>Configured shapes can only be registered server-side via a data pack, under the {@code configured_shape} data resource folder.
 *
 * @author Hugman
 * @see DawnRegistries#CONFIGURED_SHAPE
 * @see ShapeCommand
 * @see ShapeFeature
 * @since 4.0.0
 */
public record ConfiguredShape(Shape shape, ShapeProcessor processor) {
    public static final Codec<ConfiguredShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Shape.MAP_CODEC.forGetter(ConfiguredShape::shape),
            ShapeProcessor.MAP_CODEC.forGetter(ConfiguredShape::processor)
    ).apply(instance, ConfiguredShape::new));

    /**
     * Returns the processed shape as the Shape object from the Terraform shapes API.
     *
     * @param random the random instance
     */
    public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
        return this.processor.process(this.shape.get(random), random);
    }
}