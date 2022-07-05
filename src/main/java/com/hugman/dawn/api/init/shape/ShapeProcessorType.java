package com.hugman.dawn.api.init.shape;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.DawnRegistries;
import com.hugman.dawn.api.object.shape.processor.*;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;

public record ShapeProcessorType<P extends ShapeProcessor>(Codec<P> codec) {
	public static final ShapeProcessorType<AddShapeProcessor> ADD = register("add", AddShapeProcessor.CODEC);
	public static final ShapeProcessorType<SubtractShapeProcessor> SUBTRACT = register("subtract", SubtractShapeProcessor.CODEC);
	public static final ShapeProcessorType<ExcludeShapeProcessor> EXCLUDE = register("exclude", ExcludeShapeProcessor.CODEC);
	public static final ShapeProcessorType<IntersectShapeProcessor> INTERSECT = register("intersect", IntersectShapeProcessor.CODEC);

	public static final ShapeProcessorType<TranslateShapeProcessor> TRANSLATE = register("translate", TranslateShapeProcessor.CODEC);
	public static final ShapeProcessorType<RotateShapeProcessor> ROTATE = register("rotate", RotateShapeProcessor.CODEC);
	public static final ShapeProcessorType<ScaleShapeProcessor> SCALE = register("scale", ScaleShapeProcessor.CODEC);
	public static final ShapeProcessorType<NoiseTranslateShapeProcessor> NOISE_TRANSLATE = register("noise_translate", NoiseTranslateShapeProcessor.CODEC);

	private static <P extends ShapeProcessor> ShapeProcessorType<P> register(String id, Codec<P> codec) {
		return Registry.register(DawnRegistries.SHAPE_PROCESSOR_TYPE, Dawn.MOD_DATA.id(id), new ShapeProcessorType<>(codec));
	}
}
