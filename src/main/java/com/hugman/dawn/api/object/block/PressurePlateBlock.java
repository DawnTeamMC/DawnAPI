package com.hugman.dawn.api.object.block;

import com.hugman.dawn.api.object.RegistrableBlock;

public class PressurePlateBlock extends net.minecraft.block.PressurePlateBlock implements RegistrableBlock {
	public PressurePlateBlock(ActivationRule sensitivity, Settings settings) {
		super(sensitivity, settings);
	}
}
