package com.hugman.dawn.api.creator;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a recipe serializer to be created.
 *
 * @param <S> the recipe serializer class, inheriting {@link RecipeSerializer}
 */
public class RecipeSerializerCreator<S extends RecipeSerializer<? extends Recipe<?>>> extends SimpleCreator<S>
{

	/**
	 * Creates a recipe serializer.
	 *
	 * @param name       the name of the recipe serializer
	 * @param serializer the recipe serializer itself
	 */
	public RecipeSerializerCreator(String name, S serializer) {
		super(Registry.RECIPE_SERIALIZER, name, serializer);
	}
}
