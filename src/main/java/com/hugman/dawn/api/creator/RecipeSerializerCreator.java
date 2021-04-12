package com.hugman.dawn.api.creator;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.registry.Registry;

public class RecipeSerializerCreator<S extends RecipeSerializer<? extends Recipe<?>>> extends SimpleCreator<S> {
	public RecipeSerializerCreator(String name, S serializer) {
		super(Registry.RECIPE_SERIALIZER, name, serializer);
	}
}
