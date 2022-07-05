package com.hugman.dawn.api.util;

import com.hugman.dawn.api.init.shape.Shape;
import com.hugman.dawn.api.init.shape.ShapeProcessor;
import com.hugman.dawn.api.object.shape.ConfiguredShape;
import com.hugman.dawn.api.object.shape.processor.ConfiguredShapeProcessor;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public final class DawnCodecs {
	public static final Codec<Shape> SHAPE = Shape.TYPE_CODEC;
	public static final Codec<ConfiguredShape> CONFIGURED_SHAPE = configuredShape();

	public static final Codec<ShapeProcessor> SHAPE_PROCESSOR = ShapeProcessor.TYPE_CODEC;
	public static final Codec<ConfiguredShapeProcessor> CONFIGURED_SHAPE_PROCESSOR = configuredShapeProcessor();

	public static final Codec<IntProvider> INT = IntProvider.VALUE_CODEC;
	public static final Codec<IntProvider> INT_POSITIVE = IntProvider.POSITIVE_CODEC;
	public static final Codec<IntProvider> INT_NON_ZERO = nonZeroIntProvider();
	public static final Codec<FloatProvider> FLOAT = FloatProvider.VALUE_CODEC;
	public static final Codec<FloatProvider> FLOAT_NON_ZERO = nonZeroFloatProvider();

	private static Codec<ConfiguredShape> configuredShape() {
		return Codec.either(DawnCodecs.SHAPE, ConfiguredShape.CODEC).xmap((either) -> either.map(shape -> new ConfiguredShape(shape, Collections.emptyList()), configured -> configured),
				configured -> configured.processors().isEmpty() ? Either.left(configured.shape()) : Either.right(configured));
	}

	private static Codec<ConfiguredShapeProcessor> configuredShapeProcessor() {
		return Codec.either(DawnCodecs.SHAPE_PROCESSOR, ConfiguredShapeProcessor.CODEC).xmap((either) -> either.map(processor -> new ConfiguredShapeProcessor(processor, ConstantIntProvider.create(1)), configured -> configured),
				configured -> (configured.repeat().getMax() == 1 && configured.repeat().getMin() == 1) ? Either.left(configured.processor()) : Either.right(configured));
	}

	private static Codec<IntProvider> nonZeroIntProvider() {
		Function<IntProvider, DataResult<IntProvider>> function = (provider) -> {
			if (provider.getMin() <= 0 && provider.getMax() >= 0) {
				return DataResult.error("Value provider should not contain the zero value: [" + provider.getMin() + "-" + provider.getMax() + "]");
			}
			return DataResult.success(provider);
		};
		return DawnCodecs.INT.flatXmap(function, function);
	}

	private static Codec<FloatProvider> nonZeroFloatProvider() {
		Function<FloatProvider, DataResult<FloatProvider>> function = (provider) -> {
			if (provider.getMin() <= 0.0F && provider.getMax() >= 0.0F) {
				return DataResult.error("Value provider should not contain the zero value: [" + provider.getMin() + "-" + provider.getMax() + "]");
			}
			return DataResult.success(provider);
		};
		return DawnCodecs.FLOAT.flatXmap(function, function);
	}
}
