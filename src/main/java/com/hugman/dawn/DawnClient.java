package com.hugman.dawn;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.pack.ModdedPack;
import com.hugman.dawn.api.creator.pack.PackManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;

public class DawnClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register(minecraftClient -> onClientLoad(minecraftClient));
	}

	public void onClientLoad(MinecraftClient minecraftClient) {
		for(ModdedPack moddedPack : PackManager.MODDED_PACKS) {
			for(Object creator : moddedPack.getCreators()) {
				((Creator<?>) creator).clientRegister();
			}
		}
		;
		Dawn.DEBUG_WRITER.load();
	}
}
