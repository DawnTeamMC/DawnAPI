package com.hugman.dawn.api.object.block;

import com.hugman.dawn.api.object.RegistrableBlock;
import net.minecraft.block.AbstractBlock;

public class WoodButtonBlock extends net.minecraft.block.WoodenButtonBlock implements RegistrableBlock {
	public WoodButtonBlock(AbstractBlock.Settings settings) {
		super(settings);
	}
}