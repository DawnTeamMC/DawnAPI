package com.hugman.dawn.mod.util.debug.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EntityTypeEntryData extends EntryData {
	@Expose
	protected List<EntityTypeData> entries;

	public EntityTypeEntryData(String namespace, Set<Identifier> set) {
		super(namespace, Registry.ENTITY_TYPE);
		this.entries = new ArrayList<>();
		for(Identifier entityTypeId : set) {
			this.entries.add(new EntityTypeData(entityTypeId, Registry.ENTITY_TYPE.get(entityTypeId)));
		}
	}

	public static class EntityTypeData {
		@Expose
		protected Identifier name;
		@Expose
		protected Properties properties;

		public EntityTypeData(Identifier ID, EntityType<?> entityType) {
			this.name = ID;
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
}
