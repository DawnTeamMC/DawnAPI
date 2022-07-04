package com.hugman.dawn.api;

import com.google.common.reflect.Reflection;
import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.init.shape.ShapeType;
import com.hugman.dawn.api.init.shape.ShapeProcessorType;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.registry.SimpleRegistry;

public class DawnRegistries {
	public static final SimpleRegistry<ShapeType> SHAPE_TYPE = FabricRegistryBuilder.createSimple(ShapeType.class, Dawn.MOD_DATA.id("shape_type")).buildAndRegister();
	public static final SimpleRegistry<ShapeProcessorType> SHAPE_PROCESSOR_TYPE = FabricRegistryBuilder.createSimple(ShapeProcessorType.class, Dawn.MOD_DATA.id("shape_processor_type")).buildAndRegister();

	public static void init() {
		Reflection.initialize(ShapeType.class);
		Reflection.initialize(ShapeProcessorType.class);
	}
}
