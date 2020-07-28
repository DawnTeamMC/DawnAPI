package com.hugman.dawn.api.creator.pack;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.ModData;

import java.util.ArrayList;
import java.util.List;

public abstract class Pack<V> {
	protected ModData modData;
	protected final List<Creator<V>> builders = new ArrayList<>();

	public void setModData(ModData modData) {
		this.modData = modData;
	}

	public <C extends Creator<V>> C add(C.Builder<V> creatorBuilder) {
		C creator = creatorBuilder.build(modData);
		builders.add(creator);
		return creator;
	}

	public <P extends Pack<V>> P add(P pack) {
		pack.setModData(modData);
		pack.getCreators().forEach(creator -> builders.add(creator));
		return pack;
	}

	public List<Creator<V>> getCreators() {
		return builders;
	}
}
