package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.registry.Registry;

public class RecipeSerializerCreator<S extends RecipeSerializer<? extends Recipe<?>>> extends Creator {
	private RecipeSerializerCreator(ModData modData, String name, S serializer) {
		super(modData, name, serializer);
	}

	@Override
	public void register() {
		Registry.register(Registry.RECIPE_SERIALIZER, modData.id(name), value);
	}

	public static class Builder<S extends RecipeSerializer<? extends Recipe<?>>> implements CreatorBuilder<S> {
		protected final String name;
		protected final S serializer;

		/**
		 * Creates a screen handler.
		 *
		 * @param name       The name of the screen handler.
		 * @param serializer The serializer. (ex: <code>new SpecialRecipeSerializer<>(FireworkRocketRecipe::new)</code>)
		 */
		public Builder(String name, S serializer) {
			this.name = name;
			this.serializer = serializer;
		}

		public RecipeSerializerCreator<S> build(ModData modData) {
			return new RecipeSerializerCreator<>(modData, this.name, this.serializer);
		}
	}
}
