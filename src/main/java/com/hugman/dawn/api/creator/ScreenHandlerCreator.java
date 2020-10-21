package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlerCreator<T extends ScreenHandler> extends Creator<ScreenHandlerType<T>> {
	private ScreenHandlerCreator(ModData modData, String name, ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory) {
		super(modData, name, ScreenHandlerRegistry.registerSimple(modData.id(name), factory));
	}

	public static class Builder<T extends ScreenHandler> implements CreatorBuilder<ScreenHandlerType<T>> {
		protected final String name;
		protected final ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory;

		/**
		 * Creates a screen handler.
		 *
		 * @param name    The name of the screen handler.
		 * @param factory The screen handler factory. (ex: <code>FurnaceScreenHandler::new</code>)
		 */
		public Builder(String name, ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory) {
			this.name = name;
			this.factory = factory;
		}

		public ScreenHandlerCreator<T> build(ModData modData) {
			return new ScreenHandlerCreator<>(modData, this.name, this.factory);
		}
	}
}
