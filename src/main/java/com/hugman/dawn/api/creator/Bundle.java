package com.hugman.dawn.api.creator;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.util.ModData;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;

import java.util.ArrayList;
import java.util.List;

public abstract class Bundle extends Creator {
	private final List<Creator> list = new ArrayList<>();

	public void register(ModData modData) {
		this.list.forEach(creator -> creator.register(modData));
	}

	@Override
	public void clientRegister(ModData modData, ArtificeResourcePack.ClientResourcePackBuilder rp) {
		this.list.forEach(creator -> creator.clientRegister(modData, rp));
	}

	@Override
	public void serverRegister(ModData modData, ArtificeResourcePack.ServerResourcePackBuilder dp, boolean isDedicatedServer) {
		this.list.forEach(creator -> creator.serverRegister(modData, dp, isDedicatedServer));
	}

	public <V extends Creator> V put(V creator) {
		this.list.add(creator);
		return creator;
	}
}
