package com.hugman.dawn.mod.init;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackBuilder;
import com.hugman.dawn.api.util.CreatorBuilder;

public abstract class DawnPack extends Pack {
	protected static <V, B extends CreatorBuilder> V register(B creatorBuilder) {
		return add(creatorBuilder, Dawn.MOD_DATA);
	}

	protected static <P extends Pack, B extends PackBuilder> P register(B packBuilder) {
		return add(packBuilder, Dawn.MOD_DATA);
	}
}
