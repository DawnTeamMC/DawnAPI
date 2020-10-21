package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.registry.Registry;

public class EntityCreator extends Creator<EntityType<? extends Entity>> {
	protected final DefaultAttributeContainer.Builder attributeBuilder;

	private EntityCreator(ModData modData, String name, EntityType<? extends Entity> entityType, DefaultAttributeContainer.Builder attributeBuilder) {
		super(modData, name, entityType);
		this.attributeBuilder = attributeBuilder;
	}

	@Override
	public void register() {
		Registry.register(Registry.ENTITY_TYPE, modData.id(name), value);
		if(attributeBuilder != null) {
			FabricDefaultAttributeRegistry.register((EntityType<? extends LivingEntity>) value, attributeBuilder);
		}
	}

	public static class Builder implements CreatorBuilder<EntityType<? extends Entity>> {
		protected final String name;
		protected final EntityType<? extends Entity> baseEntityType;
		protected DefaultAttributeContainer.Builder attributeBuilder;

		/**
		 * Creates an entity type.
		 *
		 * @param name           The name of the entity type.
		 * @param baseEntityType The entity type itself.
		 */
		public Builder(String name, EntityType<? extends Entity> baseEntityType) {
			this.name = name;
			this.baseEntityType = baseEntityType;
		}

		public Builder attributes(DefaultAttributeContainer.Builder attributeBuilder) {
			this.attributeBuilder = attributeBuilder;
			return this;
		}

		public EntityCreator build(ModData modData) {
			return new EntityCreator(modData, this.name, this.baseEntityType, this.attributeBuilder);
		}
	}
}
