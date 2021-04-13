package com.hugman.dawn.mod.init;

import com.hugman.dawn.api.creator.EntityCreator;
import com.hugman.dawn.mod.object.entity.CustomTNTEntity;
import com.hugman.dawn.mod.object.entity.FlyingBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class DawnEntities extends DawnBundle {
	public static final EntityType<CustomTNTEntity> CUSTOM_TNT = register(new EntityCreator<>("custom_tnt", FabricEntityTypeBuilder.<CustomTNTEntity>create(SpawnGroup.MISC, CustomTNTEntity::new).fireImmune().dimensions(EntityDimensions.fixed(0.98F, 0.98F)).trackRangeChunks(10).trackedUpdateRate(10).forceTrackedVelocityUpdates(true).build()));
	public static final EntityType<FlyingBlockEntity> FLYING_BLOCK = register(new EntityCreator<>("flying_block", FabricEntityTypeBuilder.<FlyingBlockEntity>create(SpawnGroup.MISC, FlyingBlockEntity::new).dimensions(EntityDimensions.fixed(0.98F, 0.98F)).trackRangeChunks(10).trackedUpdateRate(20).forceTrackedVelocityUpdates(true).build()));

	public static void init() {
	}
}