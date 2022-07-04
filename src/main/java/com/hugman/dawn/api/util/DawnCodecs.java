package com.hugman.dawn.api.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;

import java.util.function.Function;

public final class DawnCodecs {
	public static final Codec<IntProvider> NON_ZERO_INT_PROVIDER_CODEC = nonZeroIntProvider();
	public static final Codec<FloatProvider> NON_ZERO_FLOAT_PROVIDER_CODEC = nonZeroFloatProvider();

	private static Codec<IntProvider> nonZeroIntProvider() {
		Function<IntProvider, DataResult<IntProvider>> function = (provider) -> {
			if (provider.getMin() <= 0 && provider.getMax() >= 0) {
				return DataResult.error("Value provider should not contain the zero value: [" + provider.getMin() + "-" + provider.getMax() + "]");
			}
			return DataResult.success(provider);
		};
		return IntProvider.VALUE_CODEC.flatXmap(function, function);
	}

	private static Codec<FloatProvider> nonZeroFloatProvider() {
		Function<FloatProvider, DataResult<FloatProvider>> function = (provider) -> {
			if (provider.getMin() <= 0.0F && provider.getMax() >= 0.0F) {
				return DataResult.error("Value provider should not contain the zero value: [" + provider.getMin() + "-" + provider.getMax() + "]");
			}
			return DataResult.success(provider);
		};
		return FloatProvider.VALUE_CODEC.flatXmap(function, function);
	}
}
