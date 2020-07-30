package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.SimpleBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlerCreator extends Creator<ScreenHandlerType<? extends ScreenHandler>> {
	protected final ScreenHandlerRegistry.SimpleClientHandlerFactory<? extends ScreenHandler> factory;

	private ScreenHandlerCreator(String name, ScreenHandlerRegistry.SimpleClientHandlerFactory<? extends ScreenHandler> factory) {
		super(name);
		this.factory = factory;
	}

	@Override
	public ScreenHandlerType<? extends ScreenHandler> register(ModData modData) {
		value = ScreenHandlerRegistry.registerSimple(modData.id(name), factory);
		return value;
	}

	public static class Builder implements SimpleBuilder<ScreenHandlerCreator> {
		protected final String name;
		protected final ScreenHandlerRegistry.SimpleClientHandlerFactory<? extends ScreenHandler> factory;

		/**
		 * Creates a screen handler.
		 *
		 * @param name    The name of the screen handler.
		 * @param factory The screen handler factory. (ex: <code>FurnaceScreenHandler::new</code>)
		 */
		public Builder(String name, ScreenHandlerRegistry.SimpleClientHandlerFactory<? extends ScreenHandler> factory) {
			this.name = name;
			this.factory = factory;
		}

		public ScreenHandlerCreator build() {
			return new ScreenHandlerCreator(this.name, this.factory);
		}
	}
}
