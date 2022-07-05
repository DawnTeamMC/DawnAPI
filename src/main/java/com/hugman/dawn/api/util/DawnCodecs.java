package com.hugman.dawn.api.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;

import java.util.function.Function;

public final class DawnCodecs {
	public static final Codec<IntProvider> INT = IntProvider.VALUE_CODEC;
	public static final Codec<IntProvider> INT_POSITIVE = IntProvider.POSITIVE_CODEC;
	public static final Codec<IntProvider> INT_NON_ZERO = nonZeroIntProvider();
	public static final Codec<FloatProvider> FLOAT = FloatProvider.VALUE_CODEC;
	public static final Codec<FloatProvider> FLOAT_NON_ZERO = nonZeroFloatProvider();

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
