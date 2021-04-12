package com.hugman.dawn.api.creator;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

public class ConfiguredSurfaceBuilderCreator<SC extends SurfaceConfig> extends SimpleCreator<ConfiguredSurfaceBuilder<SC>> {
	public ConfiguredSurfaceBuilderCreator(String name, ConfiguredSurfaceBuilder<SC> builder) {
		super(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, name, builder);
	}
}
