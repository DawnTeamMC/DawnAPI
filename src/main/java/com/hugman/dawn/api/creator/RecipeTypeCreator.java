package com.hugman.dawn.api.creator;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a recipe type to be created.
 *
 * @param <S> the recipe type class, inheriting {@link RecipeType}
 */
public class RecipeTypeCreator<S extends RecipeType<? extends Recipe<?>>> extends SimpleCreator<S>
{
	/**
	 * Creates a recipe type.
	 *
	 * @param name the name of the recipe type
	 * @param type the recipe type itself
	 */
	public RecipeTypeCreator(String name, S type) {
		super(Registry.RECIPE_TYPE, name, type);
	}
}
