package com.hugman.dawn.api.creator.pack;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.util.SimpleBuilder;

import java.util.ArrayList;
import java.util.List;

public abstract class Pack {
	protected static List<Creator<?>> creators = new ArrayList<>();

	protected static <V, C extends Creator<V>, B extends SimpleBuilder<C>> V add(B creatorBuilder, ModData modData) {
		C creator = creatorBuilder.build();
		creators.add(creator);
		return creator.register(modData);
	}

	protected static <P extends Pack> P add(P.Builder packBuilder, ModData modData) {
		P pack = packBuilder.build(modData);
		creators.addAll(pack.getCreators());
		return pack;
	}

	public List<Creator<?>> getCreators() {
		return creators;
	}

	public static abstract class Builder {
		public abstract <P extends Pack> P build(ModData modData);
	}
}
