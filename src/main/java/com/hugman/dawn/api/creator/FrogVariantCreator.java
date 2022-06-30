package com.hugman.dawn.api.creator;

import net.minecraft.entity.passive.FrogVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a frog variant to be created.
 */
public class FrogVariantCreator extends SimpleCreator<FrogVariant>
{
	/**
	 * Creates a frog variant.
	 *
	 * @param name    the name of the frog variant
	 * @param texture the identifier to the texture (such as '<code>minecraft:textures/entity/cat/calico.png</code>')
	 */
	public FrogVariantCreator(String name, Identifier texture) {
		super(Registry.FROG_VARIANT, name, new FrogVariant(texture));
	}
}
