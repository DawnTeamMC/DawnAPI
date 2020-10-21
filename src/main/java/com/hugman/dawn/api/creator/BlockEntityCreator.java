package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

public class BlockEntityCreator extends Creator<BlockEntityType<? extends BlockEntity>> {
	private BlockEntityCreator(String name, BlockEntityType.Builder<? extends BlockEntity> builder, ModData modData) {
		super(modData, name, builder.build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, name)));
	}

	@Override
	public void register() {
		Registry.register(Registry.BLOCK_ENTITY_TYPE, modData.id(name), value);
	}

	public static class Builder implements CreatorBuilder<BlockEntityType<? extends BlockEntity>> {
		protected final String name;
		protected final BlockEntityType.Builder<? extends BlockEntity> builder;

		/**
		 * Creates a block entity type.
		 *
		 * @param name    The name of the block entity.
		 * @param builder The block entity type builder.
		 */
		public Builder(String name, BlockEntityType.Builder<? extends BlockEntity> builder) {
			this.name = name;
			this.builder = builder;
		}

		public BlockEntityCreator build(ModData modData) {
			return new BlockEntityCreator(this.name, this.builder, modData);
		}
	}
}
