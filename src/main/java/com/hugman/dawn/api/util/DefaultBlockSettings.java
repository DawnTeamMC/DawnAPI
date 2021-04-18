package com.hugman.dawn.api.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class DefaultBlockSettings {
	public static final FabricBlockSettings LOG = FabricBlockSettings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD);
	public static final FabricBlockSettings STEM = FabricBlockSettings.of(Material.NETHER_WOOD).strength(2.0F).sounds(BlockSoundGroup.NETHER_STEM);

	public static final FabricBlockSettings LEAVES = FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(DefaultBlockSettings::canSpawnOnLeaves).suffocates(DefaultBlockSettings::never).blockVision(DefaultBlockSettings::never);
	public static final FabricBlockSettings LEAF_PILE = FabricBlockSettings.of(Material.LEAVES).strength(0.1F).ticksRandomly().sounds(BlockSoundGroup.GRASS).noCollision().nonOpaque();
	public static final FabricBlockSettings FLOWER_PILE = FabricBlockSettings.of(Material.PLANT).breakInstantly().sounds(BlockSoundGroup.GRASS).noCollision();
	public static final FabricBlockSettings SAPLING = FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.GRASS).breakInstantly().noCollision().ticksRandomly();
	public static final FabricBlockSettings FUNGUS = FabricBlockSettings.of(Material.PLANT).sounds(BlockSoundGroup.FUNGUS).breakInstantly().noCollision();
	public static final FabricBlockSettings POTTED_PLANT = FabricBlockSettings.of(Material.DECORATION).breakInstantly().nonOpaque();

	public static final FabricBlockSettings STONE_PRESSURE_PLATE = FabricBlockSettings.of(Material.STONE).requiresTool().noCollision().strength(0.5F);
	public static final FabricBlockSettings WOOD_PRESSURE_PLATE = FabricBlockSettings.of(Material.WOOD).noCollision().strength(0.5F).sounds(BlockSoundGroup.WOOD);

	public static final FabricBlockSettings STONE_BUTTON = FabricBlockSettings.of(Material.DECORATION).noCollision().hardness(0.5F);
	public static final FabricBlockSettings WOOD_BUTTON = FabricBlockSettings.of(Material.DECORATION).noCollision().hardness(0.5F).sounds(BlockSoundGroup.WOOD);

	public static final FabricBlockSettings MUSHROOM_BLOCK = FabricBlockSettings.of(Material.WOOD).hardness(0.2F).sounds(BlockSoundGroup.WOOD);
	public static final FabricBlockSettings MUSHROOM = FabricBlockSettings.of(Material.PLANT).noCollision().hardness(0.0F).sounds(BlockSoundGroup.GRASS).lightLevel(1);

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
