package fr.hugman.dawn.registry;

import fr.hugman.dawn.Dawn;
import fr.hugman.dawn.DawnFactory;
import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.shape.ConfiguredShape;
import fr.hugman.dawn.shape.Shape;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.gen.structure.Structure;

public class DawnRegistryKeys {
	public static final RegistryKey<Registry<ShapeType<?>>> SHAPE_TYPE = RegistryKey.ofRegistry(Dawn.id("shape_type"));
	public static final RegistryKey<Registry<ShapeProcessorType<?>>> SHAPE_PROCESSOR_TYPE = RegistryKey.ofRegistry(Dawn.id("shape_processor_type"));

	public static final RegistryKey<Registry<ConfiguredShape>> CONFIGURED_SHAPE = RegistryKey.ofRegistry(Dawn.id("configured_shape"));
}
