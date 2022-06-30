package com.hugman.dawn.api.util;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public final class FeatureRegistrer {
	public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> config(Identifier id, F feature, FC config) {
		return ConfiguredFeatures.register(id.toString(), feature, config);
	}

	public static RegistryEntry<PlacedFeature> place(Identifier id, RegistryEntry<? extends ConfiguredFeature<?, ?>> feature, List<PlacementModifier> modifiers) {
		return PlacedFeatures.register(id.toString(), feature, modifiers);
	}

	public static RegistryEntry<PlacedFeature> place(Identifier id, RegistryEntry<? extends ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		return place(id, feature, List.of(modifiers));
	}
}
