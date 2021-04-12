package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class SoundCreator extends SimpleCreator<SoundEvent> {
	public SoundCreator(ModData modData, String name) {
		super(Registry.SOUND_EVENT, name, null);
	}
}
