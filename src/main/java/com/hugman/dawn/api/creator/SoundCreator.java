package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a sound event to be created.
 */
public class SoundCreator extends Creator {
	private final String name;
	private SoundEvent sound;

	/**
	 * Creates a sound event.
	 *
	 * @param name the name of the sound event
	 */
	public SoundCreator(String name) {
		this.name = name;
	}

	public SoundEvent getSound() {
		return sound;
	}

	@Override
	public void register(ModData modData) {
		Identifier id = modData.id(this.name);
		this.sound = Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
	}
}
