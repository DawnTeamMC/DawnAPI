package com.hugman.dawn.mod.init;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.bundle.Bundle;

public abstract class DawnBundle extends Bundle {
	protected static <O, V extends Creator<O>> O register(V creator) {
		Dawn.MOD_DATA.addCreator(creator);
		return creator.getValue();
	}

	protected static <V extends Bundle> V register(V bundle) {
		Dawn.MOD_DATA.addBundle(bundle);
		return bundle;
	}
}
