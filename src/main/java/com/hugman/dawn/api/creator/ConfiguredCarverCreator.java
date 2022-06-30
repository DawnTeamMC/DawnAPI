package com.hugman.dawn.api.creator;

import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;

/**
 * A class allowing a configured carver to be created.
 *
 * @param <CC> the carver config class, inheriting {@link CarverConfig}
 */
public class ConfiguredCarverCreator<CC extends CarverConfig> extends SimpleCreator<ConfiguredCarver<CC>>
{

	/**
	 * Creates a configured carver.
	 *
	 * @param name   the name of the configured carver
	 * @param carver the configured carver itself
	 */
	public ConfiguredCarverCreator(String name, ConfiguredCarver<CC> carver) {
		super(BuiltinRegistries.CONFIGURED_CARVER, name, carver);
	}
}
