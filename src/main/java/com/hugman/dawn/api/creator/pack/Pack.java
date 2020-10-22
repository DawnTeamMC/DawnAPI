package com.hugman.dawn.api.creator.pack;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;

public abstract class Pack {
	protected static <V, C extends Creator<V>, B extends CreatorBuilder<V>> V add(B creatorBuilder, ModData modData) {
		C creator = creatorBuilder.build(modData);
		PackManager.CREATORS.add(creator);
		return creator.getValue();
	}

	protected static <P extends Pack, B extends PackBuilder> P add(B packBuilder, ModData modData) {
		return packBuilder.build(modData);
	}
}
