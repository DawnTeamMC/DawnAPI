package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class FeatureCreator<C extends FeatureConfig, F extends Feature<C>> extends Creator<F> {
	private FeatureCreator(ModData modData, String name, F feature) {
		super(modData, name, feature);
	}

	@Override
	public void register() {
		Registry.register(Registry.FEATURE, modData.id(name), value);
	}

	public static class Builder<C extends FeatureConfig, F extends Feature<C>> implements CreatorBuilder<F> {
		protected final String name;
		protected final F feature;

		/**
		 * Creates a stat.
		 *
		 * @param name The name of the stat.
		 */
		public Builder(String name, F feature) {
			this.name = name;
			this.feature = feature;
		}

		public FeatureCreator build(ModData modData) {
			return new FeatureCreator(modData, this.name, this.feature);
		}
	}
}
