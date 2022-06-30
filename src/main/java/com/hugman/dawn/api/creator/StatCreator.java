package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a custom stat to be created.
 */
public class StatCreator extends Creator
{
	private final String name;
	private final StatFormatter formatter;
	private Identifier stat;

	/**
	 * Creates a custom stat.
	 *
	 * @param name      the name of the custom stat
	 * @param formatter the formatter the custom stat will use
	 */
	public StatCreator(String name, StatFormatter formatter) {
		this.name = name;
		this.formatter = formatter;
	}

	public StatCreator(String name) {
		this(name, StatFormatter.DEFAULT);
	}

	public Identifier getStat() {
		return stat;
	}

	@Override
	public void register(ModData modData) {
		Identifier id = modData.id(this.name);
		this.stat = Registry.register(Registry.CUSTOM_STAT, id, id);
		Stats.CUSTOM.getOrCreateStat(id, formatter);
	}
}
