package com.hugman.dawn.api.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public final class DefaultBlockSettings {
	public static final FabricBlockSettings LOG = FabricBlockSettings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD);
	public static final FabricBlockSettings STEM = FabricBlockSettings.of(Material.NETHER_WOOD).strength(2.0F).sounds(BlockSoundGroup.NETHER_STEM);

	public static final FabricBlockSettings FUNGUS = FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.FUNGUS).breakInstantly().noCollision();
	public static final FabricBlockSettings POTTED_PLANT = FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque();

	public static boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
		return type == EntityType.OCELOT || type == EntityType.PARROT;
	}

	public static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> entityType) {
		return false;
	}

	public static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) {
		return false;
	}
}
