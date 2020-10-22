package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ConfiguredStructureCreator<FC extends FeatureConfig, SF extends StructureFeature<FC>> extends Creator<ConfiguredStructureFeature<FC, SF>> {
	private ConfiguredStructureCreator(ModData modData, String name, ConfiguredStructureFeature<FC, SF> feature) {
		super(modData, name, feature);
	}

	@Override
	public void register() {
		Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, modData.id(name), value);
	}

	public static class Builder<FC extends FeatureConfig, SF extends StructureFeature<FC>> implements CreatorBuilder<ConfiguredStructureFeature<FC, SF>> {
		protected final String name;
		protected final ConfiguredStructureFeature<FC, SF> feature;

		/**
		 * Creates a configured structure feature.
		 *
		 * @param name                       The name of the configured structure feature.
		 * @param configuredStructureFeature The configured structure feature itself.
		 */
		public Builder(String name, ConfiguredStructureFeature<FC, SF> configuredStructureFeature) {
			this.name = name;
			this.feature = configuredStructureFeature;
		}

		public ConfiguredStructureCreator build(ModData modData) {
			return new ConfiguredStructureCreator<>(modData, this.name, this.feature);
		}
	}
}
