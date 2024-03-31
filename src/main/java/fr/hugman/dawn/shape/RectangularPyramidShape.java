package fr.hugman.dawn.shape;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record RectangularPyramidShape(FloatProvider width, FloatProvider height, FloatProvider depth) implements Shape {
    public static final MapCodec<RectangularPyramidShape> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            DawnCodecs.FLOAT_PROVIDER.fieldOf("width").forGetter(RectangularPyramidShape::width),
            DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("height").forGetter(RectangularPyramidShape::height),
            DawnCodecs.FLOAT_PROVIDER.fieldOf("depth").forGetter(RectangularPyramidShape::depth)
    ).apply(instance, RectangularPyramidShape::new));

    @Override
    public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
        return Shapes.rectangularPyramid(this.width.get(random), this.height.get(random), this.depth.get(random));
    }

    @Override
    public ShapeType<?> getType() {
        return ShapeType.RECTANGULAR_PYRAMID;
    }
}