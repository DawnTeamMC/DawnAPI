package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlerCreator<T extends ScreenHandler> extends Creator {
	protected final String name;
	protected final ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory;
	protected ScreenHandlerType<T> screenHandler;

	public ScreenHandlerCreator(String name, ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory) {
		this.name = name;
		this.factory = factory;
	}

	public ScreenHandlerType<T> getScreenHandler() {
		return screenHandler;
	}

	@Override
	public void register(ModData modData) {
		this.screenHandler = ScreenHandlerRegistry.registerSimple(modData.id(this.name), this.factory);
	}
}
