package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/**
 * A class allowing a structure feature to be created.
 *
 * @param <F> the structure feature class, inheriting {@link FeatureConfig}
 */
public class StructureFeatureCreator<F extends FeatureConfig> extends SimpleCreator<StructureFeature<F>> {

	/**
	 * Creates a structure type.
	 *
	 * @param name    the name of the structure type
	 * @param structure the structure itself
	 */
	public StructureFeatureCreator(String name, StructureFeature<F> structure, GenerationStep.Feature step) {
		super(Registry.STRUCTURE_FEATURE, name, structure);
		StructureFeature.STRUCTURE_TO_GENERATION_STEP.put(structure, step);
	}
}
