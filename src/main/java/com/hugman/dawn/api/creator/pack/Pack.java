package com.hugman.dawn.api.creator.pack;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.util.CreatorBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class Pack {
	protected static List<Creator<?>> creators = new ArrayList<>();

	protected static <V, C extends Creator<V>, B extends CreatorBuilder> V add(B creatorBuilder, ModData modData) {
		C creator = creatorBuilder.build();
		creators.add(creator);
		return creator.register(modData);
	}

	protected static <P extends Pack, B extends PackBuilder> P add(B packBuilder, ModData modData) {
		P pack = packBuilder.build(modData);
		creators.addAll(pack.getCreators());
		return pack;
	}

	public List<Creator<?>> getCreators() {
		return creators;
	}
}
