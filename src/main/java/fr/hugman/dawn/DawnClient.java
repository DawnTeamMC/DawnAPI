package fr.hugman.dawn;

import fr.hugman.dawn.client.render.entity.CustomTNTEntityRenderer;
import fr.hugman.dawn.registry.DawnEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class DawnClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerEntityRenders();
    }

    private void registerEntityRenders() {
        EntityRendererRegistry.register(DawnEntities.CUSTOM_TNT, CustomTNTEntityRenderer::new);
    }
}
