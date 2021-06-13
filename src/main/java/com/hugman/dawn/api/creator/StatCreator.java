package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StatCreator extends SimpleCreator<Identifier> {
	protected final StatFormatter formatter;

	public StatCreator(String name) {
		this(name, StatFormatter.DEFAULT);
	}

	public StatCreator(String name, StatFormatter formatter) {
		super(Registry.CUSTOM_STAT, name, null);
		this.formatter = formatter;
	}

	@Override
	public void register(ModData modData) {
		this.value = Registry.register(Registry.CUSTOM_STAT, modData.id(this.name), modData.id(this.name));
		Stats.CUSTOM.getOrCreateStat(value, formatter);
	}
}
