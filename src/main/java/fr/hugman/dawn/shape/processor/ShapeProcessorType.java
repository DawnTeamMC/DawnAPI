package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.Codec;
import fr.hugman.dawn.Dawn;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import net.minecraft.util.Identifier;

public record ShapeProcessorType<P extends ShapeProcessor>(Codec<P> codec) {
	public static final Identifier DEFAULT_ID = Dawn.id("add");

	public static final ShapeProcessorType<AddShapeProcessor> ADD = DawnFactory.shapeProcessorType(AddShapeProcessor.CODEC);
	public static final ShapeProcessorType<SubtractShapeProcessor> SUBTRACT = DawnFactory.shapeProcessorType(SubtractShapeProcessor.CODEC);
	public static final ShapeProcessorType<ExcludeShapeProcessor> EXCLUDE = DawnFactory.shapeProcessorType(ExcludeShapeProcessor.CODEC);
	public static final ShapeProcessorType<IntersectShapeProcessor> INTERSECT = DawnFactory.shapeProcessorType(IntersectShapeProcessor.CODEC);

	public static final ShapeProcessorType<TranslateShapeProcessor> TRANSLATE = DawnFactory.shapeProcessorType(TranslateShapeProcessor.CODEC);
	public static final ShapeProcessorType<RotateShapeProcessor> ROTATE = DawnFactory.shapeProcessorType(RotateShapeProcessor.CODEC);
	public static final ShapeProcessorType<ScaleShapeProcessor> SCALE = DawnFactory.shapeProcessorType(ScaleShapeProcessor.CODEC);
	public static final ShapeProcessorType<NoiseTranslateShapeProcessor> NOISE_TRANSLATE = DawnFactory.shapeProcessorType(NoiseTranslateShapeProcessor.CODEC);

	public static void init() {
		Registrar.add(DEFAULT_ID, ADD);
		Registrar.add(Dawn.id("subtract"), SUBTRACT);
		Registrar.add(Dawn.id("exclude"), EXCLUDE);
		Registrar.add(Dawn.id("intersect"), INTERSECT);

		Registrar.add(Dawn.id("translate"), TRANSLATE);
		Registrar.add(Dawn.id("rotate"), ROTATE);
		Registrar.add(Dawn.id("scale"), SCALE);
		Registrar.add(Dawn.id("noise_translate"), NOISE_TRANSLATE);
	}
}
