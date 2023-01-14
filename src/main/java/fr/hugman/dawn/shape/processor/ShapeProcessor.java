package fr.hugman.dawn.shape.processor;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.terraformersmc.terraform.shapes.api.Shape;
import fr.hugman.dawn.registry.DawnRegistries;
import net.minecraft.util.math.random.Random;

// TODO: JavaDoc
public interface ShapeProcessor {
	Codec<ShapeProcessor> TYPE_CODEC = DawnRegistries.SHAPE_PROCESSOR_TYPE.getCodec().dispatch(ShapeProcessor::getType, ShapeProcessorType::codec);

	MapCodec<ShapeProcessor> MAP_CODEC = Codec.mapEither(
			ShapeProcessor.TYPE_CODEC.listOf().fieldOf("processors"),
			TYPE_CODEC.optionalFieldOf("processor", new EmptyShapeProcessor())
	).xmap(
			either -> either.map(ListShapeProcessor::new, processor -> processor),
			processor -> processor instanceof ListShapeProcessor listProcessor ? Either.left(listProcessor.processors()) : Either.right(processor)
	);

	Codec<ShapeProcessor> CODEC = Codec.either(
			TYPE_CODEC,
			ShapeProcessor.TYPE_CODEC.listOf()
	).xmap(
			either -> either.map(processor -> processor, ListShapeProcessor::new),
			processor -> processor instanceof ListShapeProcessor listProcessor ? Either.right(listProcessor.processors()) : Either.left(processor)
	);

	ShapeProcessorType<?> getType();

	Shape process(Shape shape, Random random);
}
