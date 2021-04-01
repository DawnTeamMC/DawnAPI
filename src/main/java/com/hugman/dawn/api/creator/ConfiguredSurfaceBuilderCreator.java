package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

public class ConfiguredSurfaceBuilderCreator<SC extends SurfaceConfig> extends Creator {
	private ConfiguredSurfaceBuilderCreator(ModData modData, String name, ConfiguredSurfaceBuilder<SC> feature) {
		super(modData, name, feature);
	}

	@Override
	public void register() {
		Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, modData.id(name), value);
	}

	public static class Builder<SC extends SurfaceConfig> implements CreatorBuilder<ConfiguredSurfaceBuilder<SC>> {
		protected final String name;
		protected final ConfiguredSurfaceBuilder<SC> feature;

		/**
		 * Creates a configured surface builder.
		 *
		 * @param name                     The name of the configured surface builder.
		 * @param configuredSurfaceBuilder The configured surface builder itself.
		 */
		public Builder(String name, ConfiguredSurfaceBuilder<SC> configuredSurfaceBuilder) {
			this.name = name;
			this.feature = configuredSurfaceBuilder;
		}

		public ConfiguredSurfaceBuilderCreator<SC> build(ModData modData) {
			return new ConfiguredSurfaceBuilderCreator<>(modData, this.name, this.feature);
		}
	}
}
