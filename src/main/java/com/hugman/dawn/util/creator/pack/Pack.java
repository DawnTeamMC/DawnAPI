package com.hugman.dawn.util.creator.pack;

import com.hugman.dawn.util.creator.Creator;

import java.util.ArrayList;
import java.util.List;

public abstract class Pack<V> {
	private final List<Creator<V>> creators = new ArrayList<>();

	protected V add(Creator<V> creator) {
		this.creators.add(creator);
		return creator.getValue();
	}

	public List<Creator<V>> getCreators() {
		return creators;
	}
}
