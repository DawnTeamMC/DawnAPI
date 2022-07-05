package com.hugman.dawn.api.init.shape;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.DawnRegistries;
import com.hugman.dawn.api.object.shape.*;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;

public record ShapeType<P extends Shape>(Codec<P> codec) {
	public static final ShapeType<EmptyShape> EMPTY = register("empty", EmptyShape.CODEC);

	public static final ShapeType<RectangleShape> RECTANGLE = register("rectangle", RectangleShape.CODEC);
	public static final ShapeType<RectangularPrismShape> RECTANGULAR_PRISM = register("rectangular_prism", RectangularPrismShape.CODEC);
	public static final ShapeType<RectangularPyramidShape> RECTANGULAR_PYRAMID = register("rectangular_pyramid", RectangularPyramidShape.CODEC);

	public static final ShapeType<EllipseShape> ELLIPSE = register("ellipse", EllipseShape.CODEC);
	public static final ShapeType<EllipticalPrismShape> ELLIPTICAL_PRISM = register("elliptical_prism", EllipticalPrismShape.CODEC);
	public static final ShapeType<EllipticalPyramidShape> ELLIPTICAL_PYRAMID = register("elliptical_pyramid", EllipticalPyramidShape.CODEC);

	public static final ShapeType<TriangularPrismShape> TRIANGULAR_PRISM = register("triangular_prism", TriangularPrismShape.CODEC);

	public static final ShapeType<EllipsoidShape> ELLIPSOID = register("ellipsoid", EllipsoidShape.CODEC);
	public static final ShapeType<HemiEllipsoidShape> HEMI_ELLIPSOID = register("hemi_ellipsoid", HemiEllipsoidShape.CODEC);

	private static <P extends Shape> ShapeType<P> register(String name, Codec<P> codec) {
		return Registry.register(DawnRegistries.SHAPE_TYPE, Dawn.MOD_DATA.id(name), new ShapeType<>(codec));
	}
}
