package com.hugman.dawn.object.block.property;

import net.minecraft.util.StringIdentifiable;

public enum VerticalSlabType implements StringIdentifiable {
	NORTH("north"),
	SOUTH("south"),
	EAST("east"),
	WEST("west"),
	DOUBLE("double");

	private final String name;

	VerticalSlabType(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public String asString() {
		return this.name;
	}
}
