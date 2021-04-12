package com.hugman.dawn.api.creator;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class SoundCreator extends SimpleCreator<SoundEvent> {
	public SoundCreator(String name) {
		super(Registry.SOUND_EVENT, name, null);
	}
}
