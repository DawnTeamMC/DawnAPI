package com.hugman.dawn.api.init.shape;

import com.hugman.dawn.api.DawnRegistries;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.terraformersmc.terraform.shapes.api.Shape;
import net.minecraft.util.dynamic.RegistryElementCodec;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryCodecs;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;

import java.util.Collections;
import java.util.List;

public record ConfiguredShape(Shape shape, List<ConfiguredShapeProcessor> processors) {
	private static final Codec<ConfiguredShape> BASE_CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			Shape.CODEC.fieldOf("shape").forGetter(ConfiguredShape::shape),
			ConfiguredShapeProcessor.CODEC.listOf().fieldOf("processors").forGetter(ConfiguredShape::processors)
	).apply(instance, ConfiguredShape::new));

	public static final Codec<ConfiguredShape> CODEC = Codec.either(Shape.CODEC, BASE_CODEC).xmap(
			either -> either.map(shape -> new ConfiguredShape(shape, Collections.emptyList()), configured -> configured),
			configured -> configured.processors().isEmpty() ? Either.left(configured.shape()) : Either.right(configured));

	public static final Codec<RegistryEntry<ConfiguredShape>> REGISTRY_CODEC = RegistryElementCodec.of(DawnRegistries.CONFIGURED_SHAPE.getKey(), CODEC);
	public static final Codec<RegistryEntryList<ConfiguredShape>> REGISTRY_LIST_CODEC = RegistryCodecs.entryList(DawnRegistries.CONFIGURED_SHAPE.getKey(), CODEC);

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