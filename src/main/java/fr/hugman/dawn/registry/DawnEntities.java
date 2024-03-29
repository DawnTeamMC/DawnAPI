package fr.hugman.dawn.registry;

import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.entity.CustomTNTEntity;
import fr.hugman.dawn.entity.FlyingBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class DawnEntities {
	public static final EntityType<CustomTNTEntity> CUSTOM_TNT = FabricEntityTypeBuilder.<CustomTNTEntity>create(SpawnGroup.MISC, CustomTNTEntity::new)
			.fireImmune()
			.dimensions(EntityDimensions.fixed(0.98F, 0.98F))
			.trackRangeChunks(10)
			.trackedUpdateRate(10)
			.forceTrackedVelocityUpdates(true).build();

	public static final EntityType<FlyingBlockEntity> FLYING_BLOCK = FabricEntityTypeBuilder.<FlyingBlockEntity>create(SpawnGroup.MISC, FlyingBlockEntity::new)
			.dimensions(EntityDimensions.fixed(0.98F, 0.98F))
			.trackRangeChunks(10)
			.trackedUpdateRate(20)
			.forceTrackedVelocityUpdates(true).build();

	public static void init(Registrar r) {
		r.add("flying_block", FLYING_BLOCK);
		r.add("custom_tnt", CUSTOM_TNT);
	}
}