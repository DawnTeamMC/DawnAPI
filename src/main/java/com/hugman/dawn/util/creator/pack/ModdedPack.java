package com.hugman.dawn.util.creator.pack;

import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.creator.ModData;

import java.util.ArrayList;
import java.util.List;

public abstract class ModdedPack<V> {
	private final ModData modData;
	private final List<Creator<V>> creators = new ArrayList<>();

	protected ModdedPack(ModData modData) {
		this.modData = modData;
		PackManager.MODDED_PACKS.add(this);
	}

	public List<Creator<V>> getCreators() {
		return creators;
	}

	public final <B extends Creator.Builder<V>> V add(B builder) {
		Creator<V> creator = builder.setModData(modData).build();
		creator.register();
		creators.add(creator);
		return creator.getValue();
	}

	public final <B extends Pack.Builder<V>> B add(B builder) {
		Pack<V> pack = builder.setModData(modData).build();
		pack.getCreators().forEach(creator -> creators.add(creator));
		return builder;
	}
}
