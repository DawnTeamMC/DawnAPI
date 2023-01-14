package fr.hugman.dawn.registry;

import fr.hugman.dawn.Dawn;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;


public class DawnRegistryKeys {
	public static final RegistryKey<Registry<ShapeType<?>>> SHAPE_TYPE = RegistryKey.ofRegistry(Dawn.id("shape_type"));
	public static final RegistryKey<Registry<ShapeProcessorType<?>>> SHAPE_PROCESSOR_TYPE = RegistryKey.ofRegistry(Dawn.id("shape_processor_type"));
}
