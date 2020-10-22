package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.registry.Registry;

public class EntityCreator<E extends Entity> extends Creator<EntityType<E>> {
	protected final DefaultAttributeContainer.Builder attributeBuilder;

	private EntityCreator(ModData modData, String name, EntityType<E> entityType, DefaultAttributeContainer.Builder attributeBuilder) {
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

	public static class Builder<E extends Entity> implements CreatorBuilder<EntityType<E>> {
		protected final String name;
		protected final EntityType<E> entityType;
		protected DefaultAttributeContainer.Builder attributeBuilder;

		/**
		 * Creates an entity type.
		 *
		 * @param name       The name of the entity type.
		 * @param entityType The entity type itself.
		 */
		public Builder(String name, EntityType<E> entityType) {
			this.name = name;
			this.entityType = entityType;
		}

		public Builder<E> attributes(DefaultAttributeContainer.Builder attributeBuilder) {
			this.attributeBuilder = attributeBuilder;
			return this;
		}

		public EntityCreator<E> build(ModData modData) {
			return new EntityCreator<>(modData, this.name, this.entityType, this.attributeBuilder);
		}
	}
}
