package com.hugman.dawn.mod.object.entity.render;

import com.hugman.dawn.mod.object.entity.CustomTNTEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class CustomTNTEntityRenderer extends EntityRenderer<CustomTNTEntity> {
	private final BlockRenderManager tntBlockRenderManager;

	public CustomTNTEntityRenderer(Context context) {
		super(context);
		this.shadowRadius = 0.5F;
		this.tntBlockRenderManager = context.getBlockRenderManager();
	}

	@Override
	public void render(CustomTNTEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
		BlockState blockState = entity.getBlockState();
		matrixStack.push();
		matrixStack.translate(0.0D, 0.5D, 0.0D);
		if((float) entity.getFuse() - partialTicks + 1.0F < 10.0F) {
			float h = 1.0F - ((float) entity.getFuse() - partialTicks + 1.0F) / 10.0F;
			h = MathHelper.clamp(h, 0.0F, 1.0F);
			h *= h;
			h *= h;
			float j = 1.0F + h * 0.3F;
			matrixStack.scale(j, j, j);
		}
		matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-90.0F));
		matrixStack.translate(-0.5D, -0.5D, 0.5D);
		matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
		TntMinecartEntityRenderer.renderFlashingBlock(this.tntBlockRenderManager, blockState, matrixStack, vertexConsumerProvider, light, entity.getFuse() / 5 % 2 == 0);
		matrixStack.pop();
		super.render(entity, entityYaw, partialTicks, matrixStack, vertexConsumerProvider, light);
	}

	@Override
	public Identifier getTexture(CustomTNTEntity entity) {
		return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
	}
}