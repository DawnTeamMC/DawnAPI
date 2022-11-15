package fr.hugman.dawn.debug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class EntityTypeData {
	@Expose
	protected Identifier name;
	@Expose
	protected Properties properties;

	public EntityTypeData(Identifier id, EntityType<?> entityType) {
		this.name = id;
		this.properties = new Properties(entityType);
	}

	public static class Properties {
		@Expose
		@SerializedName("spawn_group")
		protected String spawnGroup;
		@Expose
		protected float height;
		@Expose
		protected float width;
		@Expose
		@SerializedName("max_track_distance")
		protected int maxTrackDistance;
		@Expose
		@SerializedName("track_tick_interval")
		protected int trackTickInterval;
		@Expose
		@SerializedName("always_update_velocity")
		protected boolean alwaysUpdateVelocity;

		public Properties(EntityType<?> entityType) {
			this.spawnGroup = entityType.getSpawnGroup().getName();
			this.height = entityType.getHeight();
			this.width = entityType.getWidth();
			this.maxTrackDistance = entityType.getMaxTrackDistance();
			this.trackTickInterval = entityType.getTrackTickInterval();
			this.alwaysUpdateVelocity = entityType.alwaysUpdateVelocity();
		}
	}

}
