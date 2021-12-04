package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/**
 * A class allowing a structure feature to be created.
 *
 * @param <FC> the feature config class, inheriting {@link FeatureConfig}
 * @param <S>  the structure feature class, inheriting {@link StructureFeature}
 */
public class StructureFeatureCreator<FC extends FeatureConfig, S extends StructureFeature<FC>> extends Creator {
	protected final String name;
	protected final GenerationStep.Feature step;
	protected final int spacing;
	protected final int separation;
	protected final int salt;
	protected final boolean adjustsSurface;
	protected S structure;

	/**
	 * Creates a structure feature.
	 *
	 * @param name           the name of the structure feature
	 * @param structure      the structure feature itself
	 * @param step           the generation step the structure feature will generate with
	 * @param spacing        the average horizontal distance between 2 structure features of this type
	 * @param separation     the minimum distance between 2 structure features of this type
	 * @param salt           the random and unique salt of the structure feature
	 * @param adjustsSurface whether the world surface should be adjusted to fit with the structure feature
	 */
	public StructureFeatureCreator(String name, S structure, GenerationStep.Feature step, int spacing, int separation, int salt, boolean adjustsSurface) {
		this.name = name;
		this.structure = structure;
		this.step = step;
		this.spacing = spacing;
		this.separation = separation;
		this.salt = salt;
		this.adjustsSurface = adjustsSurface;
	}

	public S getStructure() {
		return structure;
	}

	@Override
	public void register(ModData modData) {
		FabricStructureBuilder<FC, S> builder = FabricStructureBuilder.create(modData.id(name), this.structure).step(step).defaultConfig(spacing, separation, salt);
		if(adjustsSurface) builder.adjustsSurface();
		this.structure = builder.register();
	}
}
