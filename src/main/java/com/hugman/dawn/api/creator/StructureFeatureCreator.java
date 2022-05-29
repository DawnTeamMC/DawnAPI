package com.hugman.dawn.api.creator;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureConfig;

/**
 * A class allowing a structure feature to be created.
 *
 * @param <F> the structure feature class, inheriting {@link FeatureConfig}
 */
public class StructureFeatureCreator<F extends net.minecraft.world.gen.structure.StructureType> extends SimpleCreator<StructureType<F>> {

	/**
	 * Creates a structure type.
	 *
	 * @param name    the name of the structure type
	 * @param structure the structure itself
	 */
	public StructureFeatureCreator(String name, Codec<F> structure) {
		super(Registry.STRUCTURE_TYPE, name, () -> structure);
	}
}
