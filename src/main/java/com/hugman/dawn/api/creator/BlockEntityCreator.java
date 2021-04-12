package com.hugman.dawn.api.creator;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

public class BlockEntityCreator<E extends BlockEntity> extends SimpleCreator<BlockEntityType<E>> {
	public BlockEntityCreator(String name, BlockEntityType.Builder<E> builder) {
		super(Registry.BLOCK_ENTITY_TYPE, name, builder.build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, name)));
	}
}
