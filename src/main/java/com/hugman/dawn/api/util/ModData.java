package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.Creator;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModData {
	private final String modName;
	private final List<Creator<?>> CREATORS = new ArrayList<>();

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

	public void registerEverything() {
		CREATORS.forEach(creator -> {
			creator.register();
			ClientLifecycleEvents.CLIENT_STARTED.register(minecraftClient -> creator.clientRegister());
			ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> creator.serverRegister(minecraftServer.isDedicated()));
		});
	}

	public Identifier id(String s) {
		return new Identifier(modName, s);
	}
}
