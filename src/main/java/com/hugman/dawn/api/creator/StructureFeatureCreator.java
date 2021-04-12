package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class StructureFeatureCreator<FC extends FeatureConfig, S extends StructureFeature<FC>> extends Creator {
	protected final String name;
	protected final GenerationStep.Feature step;
	protected final int spacing;
	protected final int separation;
	protected final int salt;
	protected final boolean adjustsSurface;
	protected S structure;

	private StructureFeatureCreator(String name, S structure, GenerationStep.Feature step, int spacing, int separation, int salt, boolean adjustsSurface) {
		this.name = name;
		this.structure = structure;
		this.step = step;
		this.spacing = spacing;
		this.separation = separation;
		this.salt = salt;
		this.adjustsSurface = adjustsSurface;
	}

	@Override
	public void register(ModData modData) {
		FabricStructureBuilder<FC, S> builder = FabricStructureBuilder.create(modData.id(name), structure).step(step).defaultConfig(spacing, separation, salt);
		if(adjustsSurface) builder.adjustsSurface();
		this.structure = builder.register();
	}
}
