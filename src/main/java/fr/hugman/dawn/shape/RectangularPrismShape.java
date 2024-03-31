package fr.hugman.dawn.shape;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record RectangularPrismShape(FloatProvider width, FloatProvider height, FloatProvider depth) implements Shape {
    public static final MapCodec<RectangularPrismShape> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            DawnCodecs.FLOAT_PROVIDER.fieldOf("width").forGetter(RectangularPrismShape::width),
            DawnCodecs.FLOAT_PROVIDER.fieldOf("height").forGetter(RectangularPrismShape::height),
            DawnCodecs.FLOAT_PROVIDER.fieldOf("depth").forGetter(RectangularPrismShape::depth)
    ).apply(instance, RectangularPrismShape::new));

    @Override
    public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
        return Shapes.rectanglarPrism(this.width.get(random), this.height.get(random), this.depth.get(random));
    }

    @Override
    public ShapeType<?> getType() {
        return ShapeType.RECTANGULAR_PRISM;
    }
}