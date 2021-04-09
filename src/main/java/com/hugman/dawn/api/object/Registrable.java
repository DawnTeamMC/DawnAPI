package com.hugman.dawn.api.object;

import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

public interface Registrable {
	@Environment(EnvType.CLIENT)
	default void registerResources(Identifier identifier, ArtificeResourcePack.ClientResourcePackBuilder rp) {

	}

	default void registerData(Identifier identifier, ArtificeResourcePack.ServerResourcePackBuilder dp) {

	}
}
