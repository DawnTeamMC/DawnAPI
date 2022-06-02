package com.hugman.dawn.api.util;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.structure.Structure;

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
