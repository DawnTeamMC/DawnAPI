package com.hugman.dawn.testing;

import com.hugman.dawn.object.block.VerticalSlabBlock;
import com.hugman.dawn.util.creator.BlockCreator;
import com.hugman.dawn.util.creator.CreatorRegister;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class DawnBlocks extends CreatorRegister<Block> {
	public DawnBlocks(String modName) {
		super(modName);
	}

	public final Block TEST_VERTICAL_SLAB = add(new BlockCreator.Builder("test_vertical_slab", new VerticalSlabBlock(FabricBlockSettings.of(Material.CAKE))).build());
}
