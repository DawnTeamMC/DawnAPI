package com.hugman.dawn.api.init.shape;

import com.hugman.dawn.api.DawnRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.random.Random;

public interface Shape {
	Codec<Shape> CODEC = DawnRegistries.SHAPE_TYPE.getCodec().dispatch(Shape::getType, ShapeType::codec);

	ShapeType<?> getType();

	com.terraformersmc.terraform.shapes.api.Shape get(Random random);
}
