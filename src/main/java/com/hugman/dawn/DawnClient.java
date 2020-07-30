package com.hugman.dawn;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.pack.Pack;
import com.hugman.dawn.api.creator.pack.PackManager;
import com.hugman.dawn.init.client.DawnColorMaps;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class DawnClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register(minecraftClient -> onClientLoad(minecraftClient));
		DawnColorMaps.registerColors();
	}

	public void onClientLoad(MinecraftClient minecraftClient) {
		for(Creator<?> creator : PackManager.CREATORS) {
			creator.clientRegister();
		}
		Dawn.DEBUG_WRITER.load();
	}
}
