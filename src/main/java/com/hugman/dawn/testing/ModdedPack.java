package com.hugman.dawn.testing;

import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.pack.ModData;
import com.hugman.dawn.util.pack.Pack;
import com.hugman.dawn.util.pack.PackManager;

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

	public final <C extends Creator<V>> V add(C.Builder builder) {
		C creator = (C) builder.setModData(modData).build();
		creator.register();
		creators.add(creator);
		return creator.getValue();
	}

	public final <P extends Pack<V>> P add(P.Builder builder) {
		P pack = (P) builder.setModData(modData).build();
		pack.getCreators().forEach(creator -> creators.add(creator));
		return pack;
	}
}
