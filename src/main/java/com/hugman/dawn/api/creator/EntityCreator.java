package com.hugman.dawn.api.creator;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

public class EntityCreator<E extends Entity> extends SimpleCreator<EntityType<E>> {
	public EntityCreator(String name, EntityType<E> entityType) {
		super(Registry.ENTITY_TYPE, name, entityType);
	}
}
