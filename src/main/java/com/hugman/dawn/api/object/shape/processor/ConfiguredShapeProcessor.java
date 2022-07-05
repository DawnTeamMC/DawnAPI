package com.hugman.dawn.api.object.shape.processor;

import com.hugman.dawn.api.init.shape.ShapeProcessor;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;

public record ConfiguredShapeProcessor(ShapeProcessor processor, IntProvider repeat) {
	public static final Codec<ConfiguredShapeProcessor> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.SHAPE_PROCESSOR.fieldOf("processor").forGetter(ConfiguredShapeProcessor::processor),
			DawnCodecs.INT_POSITIVE.fieldOf("repeat").forGetter(ConfiguredShapeProcessor::repeat)
	).apply(instance, ConfiguredShapeProcessor::new));
}
