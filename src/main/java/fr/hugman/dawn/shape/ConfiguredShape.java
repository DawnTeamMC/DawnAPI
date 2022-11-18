package fr.hugman.dawn.shape;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.dawn.Dawn;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.registry.DawnRegistryKeys;
import fr.hugman.dawn.shape.processor.ConfiguredShapeProcessor;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.random.Random;

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

	public static final Codec<RegistryEntry<ConfiguredShape>> REGISTRY_CODEC = RegistryElementCodec.of(DawnRegistryKeys.CONFIGURED_SHAPE, CODEC);
	public static final Codec<RegistryEntryList<ConfiguredShape>> REGISTRY_LIST_CODEC = RegistryCodecs.entryList(DawnRegistryKeys.CONFIGURED_SHAPE, CODEC);

	public static final RegistryKey<ConfiguredShape> DEFAULT_ID = DawnFactory.configuredShape(Dawn.id("cube"));

	public static void bootstrap(Registerable<ConfiguredShape> configuredShapeRegisterable) {
		configuredShapeRegisterable.register(DEFAULT_ID, new ConfiguredShape(
				new RectangularPrismShape(ConstantFloatProvider.create(5), ConstantFloatProvider.create(5), ConstantFloatProvider.create(5)),
				Collections.emptyList()));
	}


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