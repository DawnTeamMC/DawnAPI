package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class SoundCreator extends Creator {
	private final String name;
	private SoundEvent sound;

	public SoundCreator(String name) {
		this.name = name;
	}

	public SoundEvent getSound() {
		return sound;
	}

	@Override
	public void register(ModData modData) {
		this.sound = new SoundEvent(modData.id(this.name));
		Registry.register(Registry.SOUND_EVENT, modData.id(this.name), new SoundEvent(modData.id(this.name)));
	}
}
