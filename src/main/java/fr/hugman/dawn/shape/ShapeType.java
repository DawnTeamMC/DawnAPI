package fr.hugman.dawn.shape;

import com.mojang.serialization.Codec;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.shape.processor.ShapeProcessor;

/**
 * A shape type is a {@link Shape shape} factory that can be registered and used thanks to its {@link Codec codec}.
 *
 * @param codec the codec used to serialize and deserialize the shape
 * @param <P>   the shape
 *
 * @author Hugman
 * @see ConfiguredShape
 * @see ShapeProcessor
 * @since 4.0.0
 */
public record ShapeType<P extends Shape>(Codec<P> codec) {
	public static final ShapeType<EmptyShape> EMPTY = DawnFactory.shapeType(EmptyShape.CODEC);

	public static final ShapeType<RectangleShape> RECTANGLE = DawnFactory.shapeType(RectangleShape.CODEC);
	public static final ShapeType<RectangularPrismShape> RECTANGULAR_PRISM = DawnFactory.shapeType(RectangularPrismShape.CODEC);
	public static final ShapeType<RectangularPyramidShape> RECTANGULAR_PYRAMID = DawnFactory.shapeType(RectangularPyramidShape.CODEC);

	public static final ShapeType<EllipseShape> ELLIPSE = DawnFactory.shapeType(EllipseShape.CODEC);
	public static final ShapeType<EllipticalPrismShape> ELLIPTICAL_PRISM = DawnFactory.shapeType(EllipticalPrismShape.CODEC);
	public static final ShapeType<EllipticalPyramidShape> ELLIPTICAL_PYRAMID = DawnFactory.shapeType(EllipticalPyramidShape.CODEC);

	public static final ShapeType<TriangularPrismShape> TRIANGULAR_PRISM = DawnFactory.shapeType(TriangularPrismShape.CODEC);

	public static final ShapeType<EllipsoidShape> ELLIPSOID = DawnFactory.shapeType(EllipsoidShape.CODEC);
	public static final ShapeType<HemiEllipsoidShape> HEMI_ELLIPSOID = DawnFactory.shapeType(HemiEllipsoidShape.CODEC);

	public static void init(Registrar r) {
		r.add("empty", EMPTY);

		r.add("rectangle", RECTANGLE);
		r.add("rectangular_prism", RECTANGULAR_PRISM);
		r.add("rectangular_pyramid", RECTANGULAR_PYRAMID);

		r.add("ellipse", ELLIPSE);
		r.add("elliptical_prism", ELLIPTICAL_PRISM);
		r.add("elliptical_pyramid", ELLIPTICAL_PYRAMID);

		r.add("triangular_prism", TRIANGULAR_PRISM);

		r.add("ellipsoid", ELLIPSOID);
		r.add("hemi_ellipsoid", HEMI_ELLIPSOID);
	}
}
