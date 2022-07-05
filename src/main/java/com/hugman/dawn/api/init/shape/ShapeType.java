package com.hugman.dawn.api.init.shape;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.DawnRegistries;
import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;

public record ShapeType<P extends Shape>(Codec<P> codec) {
	private static <P extends Shape> ShapeType<P> register(String name, Codec<P> codec) {
		Dawn.LOGGER.info("Registering shape type: " + name);
		return Registry.register(DawnRegistries.SHAPE_TYPE, Dawn.MOD_DATA.id(name), new ShapeType<>(codec));
	}
}
