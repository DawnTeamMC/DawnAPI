package com.hugman.dawn;

import com.hugman.dawn.api.object.ModData;
import com.hugman.dawn.mod.init.DawnEntities;
import com.hugman.dawn.mod.object.entity.render.CustomTNTEntityRenderer;
import com.hugman.dawn.mod.object.entity.render.FlyingBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class DawnClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register(this::onClientLoad);
		registerEntityRenders();
	}

	public void onClientLoad(MinecraftClient minecraftClient) {
		Dawn.MOD_DATA_LIST.forEach(ModData::registerCreatorsClient);
		Dawn.DEBUG_WRITER.load();
	}

	private void registerEntityRenders() {
		EntityRendererRegistry.INSTANCE.register(DawnEntities.CUSTOM_TNT, CustomTNTEntityRenderer::new);
		EntityRendererRegistry.INSTANCE.register(DawnEntities.FLYING_BLOCK, FlyingBlockEntityRenderer::new);
	}
}
