package com.hugman.dawn.api.creator.pack;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.ModData;

public abstract class ModdedPack<V> extends Pack<V> {
	protected ModdedPack(ModData modData) {
		setModData(modData);
		PackManager.MODDED_PACKS.add(this);
	}

	public <C extends Creator<V>> V register(C.Builder<V> creatorBuilder) {
		return add(creatorBuilder).register();
	}

	public <P extends Pack<V>> P register(P pack) {
		add(pack).getCreators().forEach(creator -> creator.register());
		return pack;
	}
}
