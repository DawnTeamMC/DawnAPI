package com.hugman.dawn.api.creator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing an entity type to be created.
 * @param <E> the entity class, inheriting {@link Entity}
 */
public class EntityCreator<E extends Entity> extends SimpleCreator<EntityType<E>> {

	/**
	 * Creates an entity type.
	 * @param name the name of the entity type
	 * @param entityType the entity type itself
	 */
	public EntityCreator(String name, EntityType<E> entityType) {
		super(Registry.ENTITY_TYPE, name, entityType);
	}
}
