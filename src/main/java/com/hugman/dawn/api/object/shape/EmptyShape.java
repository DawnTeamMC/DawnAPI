package com.hugman.dawn.api.object.shape;

import com.hugman.dawn.api.init.shape.Shape;
import com.hugman.dawn.api.init.shape.ShapeType;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.impl.Shapes;
import com.terraformersmc.terraform.shapes.impl.layer.pathfinder.AddLayer;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.structure.processor.NopStructureProcessor;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.random.Random;

public class EmptyShape implements Shape {
	public static final EmptyShape INSTANCE = new EmptyShape();

	public static final Codec<EmptyShape> CODEC = Codec.unit(() -> INSTANCE);

	@Override
	public ShapeType<?> getType() {
		return ShapeType.EMPTY;
	}

	@Override
	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		return com.terraformersmc.terraform.shapes.api.Shape.of((point) -> false, Position.of(0, 0, 0), Position.of(0, 0, 0));
	}
}