package com.hugman.dawn.mod.init;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.pack.block.PottedPlantBundle;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class DawnBlocks extends DawnPack {
	public static final BlockCreator DUMMY_BLOCK = register(new BlockCreator.Builder().id("dummy").block(Block::new).settings(FabricBlockSettings.copyOf(Blocks.STONE)).build());
	public static final BlockCreator DUMMY2_BLOCK = register(new BlockCreator.Builder("dummy2", Block::new, FabricBlockSettings.copyOf(Blocks.STONE)).build());
	public static final PottedPlantBundle POTTED_PLANT_BUNDLE = register(new PottedPlantBundle(new BlockCreator.Builder("dummy2", Block::new, FabricBlockSettings.copyOf(Blocks.STONE))));

	public static void init() {
	}
}