package fr.hugman.dawn.shape;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import fr.hugman.dawn.registry.DawnRegistries;
import fr.hugman.dawn.shape.processor.ShapeProcessor;
import net.minecraft.util.math.random.Random;

/**
 * A shape represents a 3D area in the world. This area can be influenced by randomization if wanted.
 * <p>It actually holds information about a basic shape, without any processing.
 * <p>A shape's configuration is provided by its {@link ShapeType type}. Therefore, this is a dynamic configuration.
 * <p>A shape can be created using its {@link Shape#CODEC codec}.
 * This codec is effectively used by {@link ConfiguredShape configured shapes}, which are objects that are shapes appended with processing information.
 * <p>Shapes can be used as position iterators thanks to the <a href="https://github.com/TerraformersMC/Terraform">Terraform shapes API</a> (which is embedded with the Dawn API).
 *
 * @author Hugman
 * @see ShapeType
 * @see ConfiguredShape
 * @see ShapeProcessor
 * @since 4.0.0
 */
public interface Shape {
    MapCodec<Shape> MAP_CODEC = DawnRegistries.SHAPE_TYPE.getCodec().dispatchMap(Shape::getType, ShapeType::codec);
    Codec<Shape> CODEC = MAP_CODEC.codec();

    ShapeType<?> getType();

    /**
     * Returns the shape as the Shape object from the <a href="https://github.com/TerraformersMC/Terraform">Terraform shapes API</a>.
     *
     * @param random the random instance
     */
    com.terraformersmc.terraform.shapes.api.Shape get(Random random);
}
