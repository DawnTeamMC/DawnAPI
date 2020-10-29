package com.hugman.dawn.api.util;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Creator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModData {
	private final String modName;
	private List<Creator<?>> CREATORS = new ArrayList<>();

	public ModData(String modName) {
		this.modName = modName;
	}

	public String getModName() {
		return modName;
	}

	public <V, B extends Creator.Builder<V>> Creator<V> registerCreator(B builder) {
		Creator<V> creator = builder.build(this);
		CREATORS.add(creator);
		return creator;
	}

	public void registerCreators() {
		CREATORS.forEach(Creator::register);
		Dawn.MOD_DATAS.add(this);
	}

	@Environment(EnvType.CLIENT)
	public void registerCreatorsClient() {
		CREATORS.forEach(Creator::clientRegister);
	}

	public void registerCreatorsServer(boolean isDedicated) {
		CREATORS.forEach(creator -> creator.serverRegister(isDedicated));
	}

	public Identifier id(String s) {
		return new Identifier(modName, s);
	}
}
