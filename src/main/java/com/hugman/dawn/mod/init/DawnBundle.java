package com.hugman.dawn.mod.init;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.SimpleCreator;
import com.hugman.dawn.api.creator.bundle.Bundle;

public abstract class DawnBundle extends Bundle {
	protected static <O, V extends SimpleCreator<O>> O add(V creator) {
		Dawn.MOD_DATA.addCreator(creator);
		return creator.getValue();
	}

	protected static <V extends Creator> V creator(V creator) {
		Dawn.MOD_DATA.addCreator(creator);
		return creator;
	}

	protected static <V extends Bundle> V bundle(V bundle) {
		Dawn.MOD_DATA.addBundle(bundle);
		return bundle;
	}
}
