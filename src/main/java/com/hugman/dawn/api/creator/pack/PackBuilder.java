package com.hugman.dawn.api.creator.pack;

import com.hugman.dawn.api.util.ModData;

public interface PackBuilder {
	<P extends Pack> P build(ModData modData);
}
