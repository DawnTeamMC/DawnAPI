package com.hugman.dawn.api.init.shape;

import com.hugman.dawn.api.DawnRegistries;
import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import net.minecraft.util.math.random.Random;

public interface ShapeProcessor {
	Codec<ShapeProcessor> CODEC = DawnRegistries.SHAPE_PROCESSOR_TYPE.getCodec().dispatch(ShapeProcessor::getType, ShapeProcessorType::codec);

	ShapeProcessorType<?> getType();

	Layer get(Random random);
}
