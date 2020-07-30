package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.SimpleBuilder;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StatCreator extends Creator<Identifier> {
	protected final StatFormatter formatter;

	private StatCreator(String name, StatFormatter formatter) {
		super(name);
		this.formatter = formatter;
	}

	@Override
	public Identifier register(ModData modData) {
		value = Registry.register(Registry.CUSTOM_STAT, modData.id(name), modData.id(name));
		return value;
	}

	public static class Builder implements SimpleBuilder {
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

		public Builder setFormatter(StatFormatter formatter) {
			this.formatter = formatter;
			return this;
		}

		public StatCreator build() {
			return new StatCreator(this.name, this.formatter);
		}
	}
}
