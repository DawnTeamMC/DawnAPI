package com.hugman.dawn.api.object.block;

import com.hugman.dawn.api.object.RegistrableBlock;
import com.hugman.dawn.api.util.StringUtil;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class StairsBlock extends net.minecraft.block.StairsBlock implements RegistrableBlock {
	public StairsBlock(Settings settings) {
		super(Blocks.STONE.getDefaultState(), settings);
	}

	@Override
	public void registerResources(Identifier identifier, ArtificeResourcePack.ClientResourcePackBuilder rp) {
		rp.addBlockState(identifier, blockStateBuilder -> blockStateBuilder
						.variant("facing=east,half=bottom,shape=straight", variant -> variant.model(StringUtil.addToPath("block/", identifier, "/straight")))
						.variant("facing=west,half=bottom,shape=straight", variant -> variant.model(StringUtil.addToPath("block/", identifier, "/straight")).rotationY(180).uvlock(true))
						.variant("facing=south,half=bottom,shape=straight", variant -> variant.model(StringUtil.addToPath("block/", identifier, "/straight")).rotationY(90).uvlock(true))
						.variant("facing=north,half=bottom,shape=straight", variant -> variant.model(StringUtil.addToPath("block/", identifier, "/straight")).rotationY(270).uvlock(true))
						.variant("facing=east,half=bottom,shape=outer_right", variant -> variant.model(StringUtil.addToPath("block/", identifier, "/outer")))
				//TODO FINISH THAT SHIT BRO
		);
	}
}
