package fr.hugman.dawn.registry;

import com.mojang.serialization.Lifecycle;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.SimpleDefaultedRegistry;
import net.minecraft.util.registry.SimpleRegistry;

public class DawnRegistries {
	public static final Registry<ShapeType<?>> SHAPE_TYPE = Registries.create(DawnRegistryKeys.SHAPE_TYPE, registry -> ShapeType.EMPTY);
	public static final Registry<ShapeProcessorType<?>> SHAPE_PROCESSOR_TYPE = Registries.create(DawnRegistryKeys.SHAPE_PROCESSOR_TYPE, registry -> ShapeProcessorType.ADD);

	public static void init() {

	}

	public static <T> FabricRegistryBuilder<T, SimpleDefaultedRegistry<T>> createDefaulted(RegistryKey<Registry<T>> registryKey, Identifier defaultId) {
		return FabricRegistryBuilder.from(new SimpleDefaultedRegistry<>(defaultId.toString(), registryKey, Lifecycle.stable(), false));
	}
}
