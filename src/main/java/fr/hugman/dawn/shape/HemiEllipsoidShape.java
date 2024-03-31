package fr.hugman.dawn.shape;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import fr.hugman.dawn.codec.DawnCodecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public record HemiEllipsoidShape(FloatProvider a, FloatProvider b, FloatProvider c) implements Shape {
    public static final MapCodec<HemiEllipsoidShape> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("a").forGetter(HemiEllipsoidShape::a),
            DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("b").forGetter(HemiEllipsoidShape::b),
            DawnCodecs.NON_ZERO_FLOAT_PROVIDER.fieldOf("c").forGetter(HemiEllipsoidShape::c)
    ).apply(instance, HemiEllipsoidShape::new));

    @Override
    public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
        return Shapes.hemiEllipsoid(this.a.get(random), this.b.get(random), this.c.get(random));
    }

    @Override
    public ShapeType<?> getType() {
        return ShapeType.HEMI_ELLIPSOID;
    }
}