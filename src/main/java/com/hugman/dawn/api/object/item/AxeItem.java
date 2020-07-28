package com.hugman.dawn.api.object.item;

import com.google.common.collect.ImmutableMap.Builder;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;

public class AxeItem extends net.minecraft.item.AxeItem {
	public static final Builder<Block, Block> BLOCK_STRIPPING_MAP = new Builder<Block, Block>().putAll(STRIPPED_BLOCKS);

	/* Extension for internal publicity */
	public AxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}
}
