package com.hugman.dawn.api.creator.pack;

import com.hugman.dawn.api.creator.ModData;

public interface PackBuilder {
	<P extends Pack> P build(ModData modData);
}
