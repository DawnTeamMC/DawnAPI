package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

/**
 * A class allowing a screen handler type to be created.
 *
 * @param <T> the screen handler class, inheriting {@link ScreenHandler}
 */
public class ScreenHandlerCreator<T extends ScreenHandler> extends Creator
{
	protected final String name;
	protected final ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory;
	protected ScreenHandlerType<T> type;

	/**
	 * Creates a screen handler type.
	 *
	 * @param name    the name of the screen handler type
	 * @param factory the factory used to create the screen handler type itself
	 */
	public ScreenHandlerCreator(String name, ScreenHandlerRegistry.SimpleClientHandlerFactory<T> factory) {
		this.name = name;
		this.factory = factory;
	}

	public ScreenHandlerType<T> getType() {
		return type;
	}

	@Override
	public void register(ModData modData) {
		this.type = ScreenHandlerRegistry.registerSimple(modData.id(this.name), this.factory);
	}
}
