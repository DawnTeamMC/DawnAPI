package com.hugman.dawn.api.object.block;

import com.hugman.dawn.api.object.RegistrableBlock;
import net.minecraft.block.Block;

public class SnowyBlock extends net.minecraft.block.SnowyBlock implements RegistrableBlock {
	public SnowyBlock(Block.Settings builder) {
		super(builder);
	}
}
