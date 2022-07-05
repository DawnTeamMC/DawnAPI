package com.hugman.dawn.api.object.shape;

import com.hugman.dawn.api.init.shape.Shape;
import com.hugman.dawn.api.object.shape.processor.ConfiguredShapeProcessor;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.random.Random;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record ConfiguredShape(Shape shape, List<ConfiguredShapeProcessor> processors) {
	public static final Codec<ConfiguredShape> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.SHAPE.fieldOf("shape").forGetter(ConfiguredShape::shape),
			DawnCodecs.CONFIGURED_SHAPE_PROCESSOR.listOf().optionalFieldOf("processors").xmap(o -> o.orElse(Collections.emptyList()), Optional::ofNullable).forGetter(ConfiguredShape::processors)
	).apply(instance, ConfiguredShape::new));

	public com.terraformersmc.terraform.shapes.api.Shape get(Random random) {
		var shape = this.shape.get(random);
		for(var processor : processors) {
			for(int i = 0; i < processor.repeat().get(random); i++) {
				shape = shape.applyLayer(processor.processor().get(random));
			}
		}
		return shape;
	}
}