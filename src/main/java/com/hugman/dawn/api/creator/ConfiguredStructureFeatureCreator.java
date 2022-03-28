package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.FeatureRegistrer;
import com.hugman.dawn.api.object.ModData;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/**
 * A class allowing a configured structure feature to be created.
 *
 * @param <FC> the feature config class, inheriting {@link FeatureConfig}
 * @param <SF> the structure feature class, inheriting {@link StructureFeature}
 */
public class ConfiguredStructureFeatureCreator<FC extends FeatureConfig, SF extends StructureFeature<FC>> extends Creator {
	protected final String name;
	protected ConfiguredStructureFeature<FC, SF> feature;
	protected RegistryEntry<ConfiguredStructureFeature<?, ?>> entry;
	protected RegistryKey<ConfiguredStructureFeature<?, ?>> key;

	/**
	 * Creates a configured structure feature.
	 *
	 * @param name    the name of the configured structure feature
	 * @param feature the configured structure feature itself
	 */
	public ConfiguredStructureFeatureCreator(String name, ConfiguredStructureFeature<FC, SF> feature) {
		this.name = name;
		this.feature = feature;
	}

	@Override
	public void register(ModData modData) {
		this.key = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY, modData.id(name));
		this.entry = FeatureRegistrer.configStructure(this.key, this.feature);
	}

	public RegistryEntry<ConfiguredStructureFeature<?, ?>> get() {
		return entry;
	}
}
