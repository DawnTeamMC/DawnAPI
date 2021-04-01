package com.hugman.dawn.api.creator;

import java.util.List;

public abstract class Bundle {
	private List<Creator> list;

	public List<Creator> open() {
		return this.list;
	}

	public <V extends Creator> V put(V creator) {
		list.add(creator);
		return creator;
	}
}
