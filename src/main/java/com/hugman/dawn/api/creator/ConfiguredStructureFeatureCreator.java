package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ConfiguredStructureFeatureCreator<FC extends FeatureConfig, SF extends StructureFeature<FC>> extends SimpleCreator<ConfiguredStructureFeature<FC, SF>> {
	public ConfiguredStructureFeatureCreator(ModData modData, String name, ConfiguredStructureFeature<FC, SF> feature) {
		super(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, name, feature);
	}
}
