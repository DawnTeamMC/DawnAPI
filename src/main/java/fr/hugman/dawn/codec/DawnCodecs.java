package fr.hugman.dawn.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;

import java.util.function.Function;

public final class DawnCodecs {
	public static final Codec<Integer> NONNEGATIVE_INT = Codecs.NONNEGATIVE_INT;
	public static final Codec<Integer> POSITIVE_INT = Codecs.POSITIVE_INT;
	public static final Codec<Integer> NONPOSITIVE_INT = rangedInt(Integer.MIN_VALUE, 0, v -> "Value must be non-positive: " + v);
	public static final Codec<Integer> NEGATIVE_INT = rangedInt(Integer.MIN_VALUE, -1, v -> "Value must be negative: " + v);
	public static final Codec<IntProvider> INT_PROVIDER = IntProvider.VALUE_CODEC;
	public static final Codec<IntProvider> POSITIVE_INT_PROVIDER = IntProvider.POSITIVE_CODEC;
	public static final Codec<IntProvider> NONNEGATIVE_INT_PROVIDER = IntProvider.NON_NEGATIVE_CODEC;
	public static final Codec<IntProvider> NON_ZERO_INT_PROVIDER = nonZeroIntProvider();

	public static final Codec<Float> POSITIVE_FLOAT = Codecs.POSITIVE_FLOAT;
	public static final Codec<Float> NONPOSITIVE_FLOAT = rangedFloat(0.0F, Float.MAX_VALUE, v -> "Value must be non-negative: " + v);
	public static final Codec<Float> NONNEGATIVE_FLOAT = rangedFloat(Float.MIN_VALUE, 0.0F, v -> "Value must be non-positive: " + v);
	public static final Codec<FloatProvider> FLOAT_PROVIDER = FloatProvider.VALUE_CODEC;
	public static final Codec<FloatProvider> NON_ZERO_FLOAT_PROVIDER = nonZeroFloatProvider();

	public static final Codec<Long> NONNEGATIVE_LONG = rangedLong(0L, Long.MAX_VALUE, v -> "Value must be non-negative: " + v);
	public static final Codec<Long> POSITIVE_LONG = rangedLong(1L, Long.MAX_VALUE, v -> "Value must be positive: " + v);
	public static final Codec<Long> NONPOSITIVE_LONG = rangedLong(Long.MIN_VALUE, 0L, v -> "Value must be non-positive: " + v);
	public static final Codec<Long> NEGATIVE_LONG = rangedLong(Long.MIN_VALUE, -1L, v -> "Value must be negative: " + v);

	private static Codec<IntProvider> nonZeroIntProvider() {
		Function<IntProvider, DataResult<IntProvider>> function = (provider) -> {
			if(provider.getMin() <= 0 && provider.getMax() >= 0) {
				return DataResult.error(() -> "Value provider should not contain the zero value: [" + provider.getMin() + "-" + provider.getMax() + "]");
			}
			return DataResult.success(provider);
		};
		return DawnCodecs.INT_PROVIDER.flatXmap(function, function);
	}

	private static Codec<FloatProvider> nonZeroFloatProvider() {
		Function<FloatProvider, DataResult<FloatProvider>> function = (provider) -> {
			if(provider.getMin() <= 0.0F && provider.getMax() >= 0.0F) {
				return DataResult.error(() -> "Value provider should not contain the zero value: [" + provider.getMin() + "-" + provider.getMax() + "]");
			}
			return DataResult.success(provider);
		};
		return DawnCodecs.FLOAT_PROVIDER.flatXmap(function, function);
	}

	public static Codec<Integer> rangedInt(int min, int max, Function<Integer, String> messageFactory) {
		Function<Integer, DataResult<Integer>> function = createRangeChecker(min, max, messageFactory);
		return Codec.INT.flatXmap(function, function);
	}

	public static Codec<Float> rangedFloat(float min, float max, Function<Float, String> messageFactory) {
		Function<Float, DataResult<Float>> function = createRangeChecker(min, max, messageFactory);
		return Codec.FLOAT.flatXmap(function, function);
	}

	public static Codec<Long> rangedLong(long min, long max, Function<Long, String> messageFactory) {
		Function<Long, DataResult<Long>> function = createRangeChecker(min, max, messageFactory);
		return Codec.LONG.flatXmap(function, function);
	}

	private static <N extends Number> Function<N, DataResult<N>> createRangeChecker(N min, N max, Function<N, String> messageFactory) {
		return value -> {
			if(((Comparable) value).compareTo(min) >= 0 && ((Comparable) value).compareTo(max) <= 0) {
				return DataResult.success(value);
			}
			return DataResult.error(() -> messageFactory.apply(value));
		};
	}
}
