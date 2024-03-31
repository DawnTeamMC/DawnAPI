package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.MapCodec;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;

// TODO: JavaDoc
public record ShapeProcessorType<P extends ShapeProcessor>(MapCodec<P> codec) {
    public static final ShapeProcessorType<EmptyShapeProcessor> EMPTY = DawnFactory.shapeProcessorType(EmptyShapeProcessor.CODEC);
    public static final ShapeProcessorType<ListShapeProcessor> LIST = DawnFactory.shapeProcessorType(ListShapeProcessor.CODEC);
    public static final ShapeProcessorType<RepeatShapeProcessor> REPEAT = DawnFactory.shapeProcessorType(RepeatShapeProcessor.CODEC);

    public static final ShapeProcessorType<AddShapeProcessor> ADD = DawnFactory.shapeProcessorType(AddShapeProcessor.CODEC);
    public static final ShapeProcessorType<SubtractShapeProcessor> SUBTRACT = DawnFactory.shapeProcessorType(SubtractShapeProcessor.CODEC);
    public static final ShapeProcessorType<ExcludeShapeProcessor> EXCLUDE = DawnFactory.shapeProcessorType(ExcludeShapeProcessor.CODEC);
    public static final ShapeProcessorType<IntersectShapeProcessor> INTERSECT = DawnFactory.shapeProcessorType(IntersectShapeProcessor.CODEC);

    public static final ShapeProcessorType<TranslateShapeProcessor> TRANSLATE = DawnFactory.shapeProcessorType(TranslateShapeProcessor.CODEC);
    public static final ShapeProcessorType<RotateShapeProcessor> ROTATE = DawnFactory.shapeProcessorType(RotateShapeProcessor.CODEC);
    public static final ShapeProcessorType<ScaleShapeProcessor> SCALE = DawnFactory.shapeProcessorType(ScaleShapeProcessor.CODEC);
    public static final ShapeProcessorType<NoiseTranslateShapeProcessor> NOISE_TRANSLATE = DawnFactory.shapeProcessorType(NoiseTranslateShapeProcessor.CODEC);

    public static void init(Registrar r) {
        r.add("empty", EMPTY);
        r.add("list", LIST);
        r.add("repeat", REPEAT);

        r.add("add", ADD);
        r.add("subtract", SUBTRACT);
        r.add("exclude", EXCLUDE);
        r.add("intersect", INTERSECT);

        r.add("translate", TRANSLATE);
        r.add("rotate", ROTATE);
        r.add("scale", SCALE);
        r.add("noise_translate", NOISE_TRANSLATE);
    }
}
