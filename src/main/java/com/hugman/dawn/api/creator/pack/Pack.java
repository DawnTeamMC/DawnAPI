package com.hugman.dawn.api.creator.pack;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.util.CreatorBuilder;

public abstract class Pack {
	protected static <V, C extends Creator<V>, B extends CreatorBuilder> V add(B creatorBuilder, ModData modData) {
		C creator = creatorBuilder.build();
		PackManager.CREATORS.add(creator);
		return creator.register(modData);
	}

	protected static <P extends Pack, B extends PackBuilder> P add(B packBuilder, ModData modData) {
		P pack = packBuilder.build(modData);
		return pack;
	}
}
