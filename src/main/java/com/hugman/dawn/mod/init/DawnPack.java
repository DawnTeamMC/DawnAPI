package com.hugman.dawn.mod.init;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.pack.Pack;

public abstract class DawnPack extends Pack {
	protected static <V, B extends Creator.Builder<V>> V register(B creatorBuilder) {
		return add(creatorBuilder, Dawn.MOD_DATA);
	}

	private static <P extends Pack, B extends Builder> P register(B packBuilder) {
		return add(packBuilder, Dawn.MOD_DATA);
	}
}
