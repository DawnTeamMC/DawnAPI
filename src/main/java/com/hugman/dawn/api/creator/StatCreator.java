package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StatCreator extends Creator<Identifier> {
	protected final StatFormatter formatter;

	private StatCreator(ModData modData, String name, StatFormatter formatter) {
		super(modData, name, modData.id(name));
		this.formatter = formatter;
	}

	@Override
	public void register() {
		Registry.register(Registry.CUSTOM_STAT, modData.id(name), value);
		Stats.CUSTOM.getOrCreateStat(value, formatter);
	}

	public static class Builder implements Creator.Builder<Identifier> {
		protected final String name;
		protected StatFormatter formatter;

		/**
		 * Creates a stat.
		 *
		 * @param name The name of the stat.
		 */
		public Builder(String name) {
			this.name = name;
			this.formatter = StatFormatter.DEFAULT;
		}

		public Builder formatter(StatFormatter formatter) {
			this.formatter = formatter;
			return this;
		}

		public StatCreator build(ModData modData) {
			return new StatCreator(modData, this.name, this.formatter);
		}
	}
}
