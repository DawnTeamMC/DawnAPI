package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.registry.Registry;

public class RecipeSerializerCreator<S extends RecipeSerializer<T>, T extends Recipe<?>> extends Creator<S> {
	protected final S serializer;

	private RecipeSerializerCreator(String name, S serializer) {
		super(name);
		this.serializer = serializer;
	}

	@Override
	public S register(ModData modData) {
		value = Registry.register(Registry.RECIPE_SERIALIZER, modData.id(name), serializer);
		return value;
	}

	public class Builder implements CreatorBuilder {
		protected final String name;
		protected final S serializer;

		/**
		 * Creates a screen handler.
		 *
		 * @param name    The name of the screen handler.
		 * @param serializer The serializer. (ex: <code>new SpecialRecipeSerializer<>(FireworkRocketRecipe::new)</code>)
		 */
		public Builder(String name, S serializer) {
			this.name = name;
			this.serializer = serializer;
		}

		public RecipeSerializerCreator<S, T> build() {
			return new RecipeSerializerCreator<>(this.name, this.serializer);
		}
	}
}
