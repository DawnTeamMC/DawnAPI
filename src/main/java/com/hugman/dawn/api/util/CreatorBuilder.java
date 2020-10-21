package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.Creator;

public interface CreatorBuilder<V> {
	<C extends Creator<V>> C build(ModData modData);
}
