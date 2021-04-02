package com.hugman.dawn.api.creator;

import java.util.ArrayList;
import java.util.List;

public abstract class Bundle {
	private final List<Creator> list = new ArrayList<>();

	public List<Creator> open() {
		return this.list;
	}

	public <V extends Creator> V put(V creator) {
		list.add(creator);
		return creator;
	}
}
