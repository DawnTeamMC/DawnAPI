package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class StructureFeatureCreator<FC extends FeatureConfig, S extends StructureFeature<FC>> extends Creator {
	protected final GenerationStep.Feature step;
	protected final int spacing;
	protected final int separation;
	protected final int salt;
	protected final boolean adjustsSurface;

	private StructureFeatureCreator(ModData modData, String name, S structure, GenerationStep.Feature step, int spacing, int separation, int salt, boolean adjustsSurface) {
		super(modData, name, structure);
		this.step = step;
		this.spacing = spacing;
		this.separation = separation;
		this.salt = salt;
		this.adjustsSurface = adjustsSurface;
	}

	@Override
	public void register() {
		FabricStructureBuilder<FC, S> builder = FabricStructureBuilder.create(modData.id(name), value).step(step).defaultConfig(spacing, separation, salt);
		if(adjustsSurface) builder.adjustsSurface();
		builder.register();
	}

	public static class Builder<FC extends FeatureConfig, S extends StructureFeature<FC>> implements CreatorBuilder<S> {
		protected final String name;
		protected final S structure;
		protected final GenerationStep.Feature step;
		protected final int spacing;
		protected final int separation;
		protected final int salt;
		protected boolean adjustsSurface;

		/**
		 * Creates a structure feature.
		 *
		 * @param name      The name of the feature.
		 * @param structure The structure itself.
		 * @param step      The generation step.
		 */
		public Builder(String name, S structure, GenerationStep.Feature step, int spacing, int separation, int salt) {
			this.name = name;
			this.structure = structure;
			this.step = step;
			this.spacing = spacing;
			this.separation = separation;
			this.salt = salt;
			this.adjustsSurface = false;
		}

		public Builder<FC, S> adjustsSurface() {
			this.adjustsSurface = true;
			return this;
		}

		public StructureFeatureCreator<FC, S> build(ModData modData) {
			return new StructureFeatureCreator<>(modData, this.name, this.structure, this.step, this.spacing, this.separation, this.salt, this.adjustsSurface);
		}
	}
}
