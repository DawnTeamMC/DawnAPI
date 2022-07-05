package com.hugman.dawn.api.init.shape;

import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;

public record ConfiguredShapeProcessor(ShapeProcessor processor, IntProvider repeat) {
	private static final Codec<ConfiguredShapeProcessor> BASE_CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			ShapeProcessor.CODEC.fieldOf("processor").forGetter(ConfiguredShapeProcessor::processor),
			DawnCodecs.INT_POSITIVE.fieldOf("repeat").forGetter(ConfiguredShapeProcessor::repeat)
	).apply(instance, ConfiguredShapeProcessor::new));

	public static final Codec<ConfiguredShapeProcessor> CODEC = Codec.either(ShapeProcessor.CODEC, BASE_CODEC).xmap(
			(either) -> either.map(processor -> new ConfiguredShapeProcessor(processor, ConstantIntProvider.create(1)), configured -> configured),
			configured -> (configured.repeat().getMax() == 1 && configured.repeat().getMin() == 1) ? Either.left(configured.processor()) : Either.right(configured));
}
