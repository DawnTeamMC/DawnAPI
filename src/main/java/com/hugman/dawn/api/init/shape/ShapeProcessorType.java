package com.hugman.dawn.api.init.shape;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.DawnRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;

public record ShapeProcessorType<P extends ShapeProcessor>(Codec<P> codec) {
	private static <P extends ShapeProcessor> ShapeProcessorType<P> register(String id, Codec<P> codec) {
		return Registry.register(DawnRegistries.SHAPE_PROCESSOR_TYPE, Dawn.MOD_DATA.id(id), new ShapeProcessorType<>(codec));
	}
}
