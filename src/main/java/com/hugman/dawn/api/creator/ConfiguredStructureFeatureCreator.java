package com.hugman.dawn.api.creator;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/**
 * A class allowing a configured structure feature to be created.
 *
 * @param <FC> the feature config class, inheriting {@link FeatureConfig}
 * @param <SF> the structure feature class, inheriting {@link StructureFeature}
 */
public class ConfiguredStructureFeatureCreator<FC extends FeatureConfig, SF extends StructureFeature<FC>> extends SimpleCreator<ConfiguredStructureFeature<FC, SF>> {

	/**
	 * Creates a configured structure feature.
	 *
	 * @param name    the name of the configured structure feature
	 * @param feature the configured structure feature itself
	 */
	public ConfiguredStructureFeatureCreator(String name, ConfiguredStructureFeature<FC, SF> feature) {
		super(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, name, feature);
	}
}
