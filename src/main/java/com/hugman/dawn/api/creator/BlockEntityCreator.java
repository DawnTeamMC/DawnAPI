package com.hugman.dawn.api.creator;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

/**
 * A class allowing a block entity type to be created.
 *
 * @param <E> the block entity class, inheriting {@link BlockEntity}
 */
public class BlockEntityCreator<E extends BlockEntity> extends SimpleCreator<BlockEntityType<E>> {

	/**
	 * Creates a block entity type.
	 *
	 * @param name    the name of the block entity type
	 * @param builder the builder used to create the block entity type itself
	 */
	public BlockEntityCreator(String name, FabricBlockEntityTypeBuilder<E> builder) {
		super(Registry.BLOCK_ENTITY_TYPE, name, builder.build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, name)));
	}
}
