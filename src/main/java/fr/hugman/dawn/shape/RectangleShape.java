package fr.hugman.dawn.shape;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record RectangleShape(FloatProvider width, FloatProvider height) implements Shape {
    public static final MapCodec<RectangleShape> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            DawnCodecs.FLOAT_PROVIDER.fieldOf("width").forGetter(RectangleShape::width),
            DawnCodecs.FLOAT_PROVIDER.fieldOf("height").forGetter(RectangleShape::height)
    ).apply(instance, RectangleShape::new));

    @Override
    public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
        return Shapes.rectangle(this.width.get(random), this.height.get(random));
    }

    @Override
    public ShapeType<?> getType() {
        return ShapeType.RECTANGLE;
    }
}