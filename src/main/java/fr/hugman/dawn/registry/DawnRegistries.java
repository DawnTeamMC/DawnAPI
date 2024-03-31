package fr.hugman.dawn.registry;

import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.shape.ConfiguredShape;
import fr.hugman.dawn.shape.ShapeType;
import fr.hugman.dawn.shape.processor.ShapeProcessorType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.ResourceType;

public class DawnRegistries {
    public static final Registry<ShapeType<?>> SHAPE_TYPE = Registries.create(DawnRegistryKeys.SHAPE_TYPE, registry -> ShapeType.EMPTY);
    public static final Registry<ShapeProcessorType<?>> SHAPE_PROCESSOR_TYPE = Registries.create(DawnRegistryKeys.SHAPE_PROCESSOR_TYPE, registry -> ShapeProcessorType.ADD);
    public static final ReloadableResourceManager<ConfiguredShape> CONFIGURED_SHAPE = ReloadableResourceManager.of(ConfiguredShape.CODEC, ResourceType.SERVER_DATA, "configured_shape");

    public static void init(Registrar r) {
        r.add("configured_shape", CONFIGURED_SHAPE);
    }
}
