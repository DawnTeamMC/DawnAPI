package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public abstract class Creator {
	public abstract void register(ModData modData);

	@Environment(EnvType.CLIENT)
	public abstract void clientRegister(ModData modData, ArtificeResourcePack.ClientResourcePackBuilder resourcePackBuilder);

	public abstract void serverRegister(ModData modData, ArtificeResourcePack.ServerResourcePackBuilder dataPackBuilder, boolean isDedicatedServer);
}
