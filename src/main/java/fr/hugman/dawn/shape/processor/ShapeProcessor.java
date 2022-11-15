package fr.hugman.dawn.shape.processor;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import fr.hugman.dawn.registry.DawnRegistries;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.Feature;

public interface ShapeProcessor {
	Codec<ShapeProcessor> CODEC = DawnRegistries.SHAPE_PROCESSOR_TYPE.getCodec().dispatch(ShapeProcessor::getType, ShapeProcessorType::codec);

	ShapeProcessorType<?> getType();

	Layer get(Random random);
}
