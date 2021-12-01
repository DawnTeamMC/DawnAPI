package com.hugman.dawn.api.object;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.bundle.Bundle;
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
		CREATORS.add(bundle);
	}

	public void registerCreators() {
		CREATORS.forEach(creator -> creator.register(this));
		CREATORS.forEach(creator -> creator.postRegister(this));
		Dawn.MOD_DATA_LIST.add(this);
	}

	@Environment(EnvType.CLIENT)
	public void registerCreatorsClient() {
		this.CREATORS.forEach(creator -> creator.clientRegister(this));
	}

	public void registerCreatorsServer(boolean isDedicated) {
		this.CREATORS.forEach(creator -> creator.serverRegister(this, isDedicated));
	}

	public Identifier id(String s) {
		return new Identifier(this.modName, s);
	}
}
