package com.hugman.dawn.api.object.block;

import com.hugman.dawn.api.object.RegistrableBlock;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.Predicate;

public class SaplingBlock extends net.minecraft.block.SaplingBlock implements RegistrableBlock {
	private final Predicate<BlockState> predicate;

	public SaplingBlock(SaplingGenerator saplingGenerator, AbstractBlock.Settings settings) {
		super(saplingGenerator, settings);
		this.predicate = null;
	}

	public SaplingBlock(SaplingGenerator saplingGenerator, Predicate<BlockState> predicate, AbstractBlock.Settings settings) {
		super(saplingGenerator, settings);
		this.predicate = predicate;
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		if(predicate != null) {
			if(predicate.test(floor)) return true;
		}
		return super.canPlantOnTop(floor, world, pos);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void registerResources(Identifier identifier, ArtificeResourcePack.ClientResourcePackBuilder rp) {
		rp.addItemModel(identifier, modelBuilder -> modelBuilder
				.parent(new Identifier("item/generated"))
				.texture("layer0", identifier));
	}
}