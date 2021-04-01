package com.hugman.dawn.api.util;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Bundle;
import com.hugman.dawn.api.creator.Creator;
import com.swordglowsblue.artifice.api.Artifice;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModData {
	private final String modName;
	private final List<Creator> CREATORS = new ArrayList<>();

	public ModData(String modName) {
		this.modName = modName;
	}

	public String getModName() {
		return modName;
	}

	public void addCreator(Creator creator) {
		CREATORS.add(creator);
	}

	public void addBundle(Bundle bundle) {
		CREATORS.addAll(bundle.open());
	}

	public void registerCreators() {
		CREATORS.forEach(creator -> creator.register(this));
		Dawn.MOD_DATA_LIST.add(this);
	}

	@Environment(EnvType.CLIENT)
	public void registerCreatorsClient() {
		Artifice.registerAssetPack(id("my_assets"), builder -> CREATORS.forEach(creator -> creator.clientRegister(this, builder)));
	}

	public void registerCreatorsServer(boolean isDedicated) {
		Artifice.registerDataPack(id("my_data"), builder -> CREATORS.forEach(creator -> creator.serverRegister(this, builder, isDedicated)));
	}

	public Identifier id(String s) {
		return new Identifier(modName, s);
	}
}
