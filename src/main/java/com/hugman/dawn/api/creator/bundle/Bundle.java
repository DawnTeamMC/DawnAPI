package com.hugman.dawn.api.creator.bundle;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.object.ModData;

import java.util.ArrayList;
import java.util.List;

public abstract class Bundle extends Creator {
	private final List<Creator> list = new ArrayList<>();

	@Override
	public void register(ModData modData) {
		this.list.forEach(creator -> creator.register(modData));
	}

	@Override
	public void postRegister(ModData modData) {
		this.list.forEach(creator -> creator.postRegister(modData));
	}

	@Override
	public void clientRegister(ModData modData) {
		this.list.forEach(creator -> creator.clientRegister(modData));
	}

	@Override
	public void serverRegister(ModData modData, boolean isDedicatedServer) {
		this.list.forEach(creator -> creator.serverRegister(modData, isDedicatedServer));
	}

	public <V extends Creator> V put(V creator) {
		this.list.add(creator);
		return creator;
	}
}
