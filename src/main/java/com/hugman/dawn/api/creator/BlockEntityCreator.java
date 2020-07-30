package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

public class BlockEntityCreator extends Creator<BlockEntityType<? extends BlockEntity>> {
	protected final BlockEntityType.Builder<? extends BlockEntity> builder;

	private BlockEntityCreator(String name, BlockEntityType.Builder<? extends BlockEntity> builder) {
		super(name);
		this.builder = builder;
	}

	@Override
	public BlockEntityType<? extends BlockEntity> register(ModData modData) {
		Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, name);
		value = Registry.register(Registry.BLOCK_ENTITY_TYPE, modData.id(name), builder.build(type));
		return value;
	}

	public static class Builder implements CreatorBuilder {
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

		public BlockEntityCreator build() {
			return new BlockEntityCreator(this.name, this.builder);
		}
	}
}
