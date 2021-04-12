package com.hugman.dawn.mod.init;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.bundle.Bundle;

public abstract class DawnBundle extends Bundle {
	protected static <V extends Creator> V register(V creator) {
		Dawn.MOD_DATA.addCreator(creator);
		return creator;
	}

	protected static <V extends Bundle> V register(V bundle) {
		Dawn.MOD_DATA.addBundle(bundle);
		return bundle;
	}
}
