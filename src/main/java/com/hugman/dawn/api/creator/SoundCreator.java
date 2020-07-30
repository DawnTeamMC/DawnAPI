package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.SimpleBuilder;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class SoundCreator extends Creator<SoundEvent> {
	private SoundCreator(String name) {
		super(name);
	}

	@Override
	public SoundEvent register(ModData modData) {
		value = Registry.register(Registry.SOUND_EVENT, modData.id(name), new SoundEvent(modData.id(name)));
		return value;
	}

	public static class Builder implements SimpleBuilder {
		protected final String name;

		/**
		 * Creates a sound.
		 *
		 * @param name The name of the sound.
		 */
		public Builder(String name) {
			this.name = name;
		}

		public SoundCreator build() {
			return new SoundCreator(this.name);
		}
	}
}
