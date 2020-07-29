package com.hugman.dawn.api.creator.pack;

import java.util.ArrayList;
import java.util.List;

public class PackManager {
	public static final List<Pack> MODDED_PACKS = new ArrayList<>();

	public static void addModdedPack(Pack pack) {
		MODDED_PACKS.add(pack);
	}
}
