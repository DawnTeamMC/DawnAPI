package com.hugman.dawn.api.creator;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

/**
 * A class allowing a configured surface builder to be created.
 * @param <SC> the surface builder config class, inheriting {@link SurfaceConfig}
 */
public class ConfiguredSurfaceBuilderCreator<SC extends SurfaceConfig> extends SimpleCreator<ConfiguredSurfaceBuilder<SC>> {

	/**
	 * Creates a configured surface builder.
	 * @param name the name of the configured surface builder
	 * @param builder the configured surface builder itself
	 */
	public ConfiguredSurfaceBuilderCreator(String name, ConfiguredSurfaceBuilder<SC> builder) {
		super(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, name, builder);
	}
}
