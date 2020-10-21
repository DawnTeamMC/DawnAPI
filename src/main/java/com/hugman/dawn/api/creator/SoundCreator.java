package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class SoundCreator extends Creator<SoundEvent> {
	private SoundCreator(ModData modData, String name) {
		super(modData, name, new SoundEvent(modData.id(name)));
	}

	@Override
	public void register() {
		Registry.register(Registry.SOUND_EVENT, modData.id(name), value);
	}

	public static class Builder implements CreatorBuilder<SoundEvent> {
		protected final String name;

		/**
		 * Creates a sound.
		 *
		 * @param name The name of the sound.
		 */
		public Builder(String name) {
			this.name = name;
		}

		public SoundCreator build(ModData modData) {
			return new SoundCreator(modData, this.name);
		}
	}
}
