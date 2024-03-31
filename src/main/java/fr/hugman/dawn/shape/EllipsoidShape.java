package fr.hugman.dawn.shape;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record EllipsoidShape(FloatProvider a, FloatProvider b, FloatProvider c) implements Shape {
    public static final MapCodec<EllipsoidShape> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("a").forGetter(EllipsoidShape::a),
            DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("b").forGetter(EllipsoidShape::b),
            DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("c").forGetter(EllipsoidShape::c)
    ).apply(instance, EllipsoidShape::new));

    @Override
    public ShapeType<?> getType() {
        return ShapeType.ELLIPSOID;
    }

    @Override
    public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
        return Shapes.ellipsoid(this.a.get(random), this.b.get(random), this.c.get(random));
    }
}