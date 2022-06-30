package com.hugman.dawn.api.creator;

import net.minecraft.entity.passive.CatVariant;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a cat variant to be created.
 */
public class CatVariantCreator extends SimpleCreator<CatVariant>
{
	/**
	 * Creates a cat variant.
	 *
	 * @param name    the name of the cat variant
	 * @param texture the identifier to the texture (such as '<code>minecraft:textures/entity/cat/calico.png</code>')
	 */
	public CatVariantCreator(String name, Identifier texture) {
		super(Registry.CAT_VARIANT, name, new CatVariant(texture));
	}
}
