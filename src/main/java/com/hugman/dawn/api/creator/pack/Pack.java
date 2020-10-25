package com.hugman.dawn.api.creator.pack;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.util.ModData;

public abstract class Pack {
	protected static <V, B extends Creator.Builder<V>> V add(B creatorBuilder, ModData modData) {
		Creator<V> creator = modData.registerCreator(creatorBuilder);
		return creator.getValue();
	}

	protected static <P extends Pack, B extends Builder> P add(B packBuilder, ModData modData) {
		return packBuilder.build(modData);
	}

	public interface Builder {
		<P extends Pack> P build(ModData modData);
	}
}
