package com.hugman.dawn.api.creator;

import com.mojang.serialization.Codec;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

/**
 * A class allowing a structure type to be created.
 *
 * @param <F> the structure feature class, inheriting {@link FeatureConfig}
 */
public class StructureTypeCreator<F extends Structure> extends SimpleCreator<StructureType<F>>
{

	/**
	 * Creates a structure type.
	 *
	 * @param name      the name of the structure type
	 * @param structure the structure itself
	 */
	public StructureTypeCreator(String name, Codec<F> structure) {
		super(Registry.STRUCTURE_TYPE, name, () -> structure);
	}
}
