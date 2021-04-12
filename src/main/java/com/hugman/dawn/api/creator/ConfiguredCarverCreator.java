package com.hugman.dawn.api.creator;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;

public class ConfiguredCarverCreator<CC extends CarverConfig> extends SimpleCreator<ConfiguredCarver<CC>> {
	public ConfiguredCarverCreator(String name, ConfiguredCarver<CC> carver) {
		super(BuiltinRegistries.CONFIGURED_CARVER, name, carver);
	}
}
