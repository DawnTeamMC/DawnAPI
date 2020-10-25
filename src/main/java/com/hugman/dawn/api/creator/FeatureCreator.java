package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class FeatureCreator<FC extends FeatureConfig, F extends Feature<FC>> extends Creator<F> {
	private FeatureCreator(ModData modData, String name, F feature) {
		super(modData, name, feature);
	}

	@Override
	public void register() {
		Registry.register(Registry.FEATURE, modData.id(name), value);
	}

	public static class Builder<FC extends FeatureConfig, F extends Feature<FC>> implements Creator.Builder<F> {
		protected final String name;
		protected final F feature;

		/**
		 * Creates a feature.
		 *
		 * @param name    The name of the feature.
		 * @param feature The configured carver itself.
		 */
		public Builder(String name, F feature) {
			this.name = name;
			this.feature = feature;
		}

		public FeatureCreator build(ModData modData) {
			return new FeatureCreator<>(modData, this.name, this.feature);
		}
	}
}
