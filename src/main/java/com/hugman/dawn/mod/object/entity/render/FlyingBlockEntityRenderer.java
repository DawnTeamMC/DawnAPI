package com.hugman.dawn.mod.object.entity.render;

import com.hugman.dawn.mod.object.entity.FlyingBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

@Environment(EnvType.CLIENT)
public class FlyingBlockEntityRenderer extends EntityRenderer<FlyingBlockEntity> {
	public FlyingBlockEntityRenderer(Context context) {
		super(context);
		this.shadowRadius = 0.5F;
	}

	@Override
	public void render(FlyingBlockEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light) {
		BlockState blockState = entity.getBlockState();
		if(blockState.getRenderType() == BlockRenderType.MODEL) {
			World world = entity.getWorldClient();
			if(blockState != world.getBlockState(entity.getBlockPos()) && blockState.getRenderType() != BlockRenderType.INVISIBLE) {
				matrixStack.push();
				BlockPos blockPos = new BlockPos(entity.getX(), entity.getBoundingBox().maxY, entity.getZ());
				matrixStack.translate(-0.5D, 0.0D, -0.5D);
				BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
				blockRenderManager.getModelRenderer().render(world, blockRenderManager.getModel(blockState), blockState, blockPos, matrixStack, vertexConsumerProvider.getBuffer(RenderLayers.getMovingBlockLayer(blockState)), false, new Random(), blockState.getRenderingSeed(entity.getFallingBlockPos()), OverlayTexture.DEFAULT_UV);
				matrixStack.pop();
				super.render(entity, entityYaw, partialTicks, matrixStack, vertexConsumerProvider, light);
			}
		}
	}

	@Override
	public Identifier getTexture(FlyingBlockEntity entity) {
		return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
	}
}