package fr.hugman.dawn.shape;

import com.mojang.serialization.Codec;
import fr.hugman.dawn.Dawn;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import net.minecraft.util.Identifier;

public record ShapeType<P extends Shape>(Codec<P> codec) {
	public static final Identifier DEFAULT_ID = Dawn.id("empty");

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

	public static void init() {
		Registrar.add(DEFAULT_ID, EMPTY);

		Registrar.add(Dawn.id("rectangle"), RECTANGLE);
		Registrar.add(Dawn.id("rectangular_prism"), RECTANGULAR_PRISM);
		Registrar.add(Dawn.id("rectangular_pyramid"), RECTANGULAR_PYRAMID);

		Registrar.add(Dawn.id("ellipse"), ELLIPSE);
		Registrar.add(Dawn.id("elliptical_prism"), ELLIPTICAL_PRISM);
		Registrar.add(Dawn.id("elliptical_pyramid"), ELLIPTICAL_PYRAMID);

		Registrar.add(Dawn.id("triangular_prism"), TRIANGULAR_PRISM);

		Registrar.add(Dawn.id("ellipsoid"), ELLIPSOID);
		Registrar.add(Dawn.id("hemi_ellipsoid"), HEMI_ELLIPSOID);
	}
}
