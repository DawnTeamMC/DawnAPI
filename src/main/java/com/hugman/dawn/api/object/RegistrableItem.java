package com.hugman.dawn.api.object;

import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

public interface RegistrableItem extends Registrable {
	@Environment(EnvType.CLIENT)
	default void registerResources(Identifier identifier, ArtificeResourcePack.ClientResourcePackBuilder rp) {
		rp.addItemModel(identifier, modelBuilder -> modelBuilder
				.parent(new Identifier("item/generated"))
				.texture("layer0", identifier));
	}
}
