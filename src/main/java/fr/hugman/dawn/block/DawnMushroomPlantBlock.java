package fr.hugman.dawn.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class DawnMushroomPlantBlock extends MushroomPlantBlock {
	private final RegistryKey<ConfiguredFeature<?, ?>> featureKey;

	public DawnMushroomPlantBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Settings settings) {
		super(settings, null);
		this.featureKey = featureKey;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.randomTick(state, world, pos, random);
	}

	public boolean trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random random) {
		world.removeBlock(pos, false);
		ConfiguredFeature<?, ?> feature = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE_WORLDGEN).get(this.featureKey);
		if(feature != null && feature.generate(world, world.getChunkManager().getChunkGenerator(), random, pos)) {
			return true;
		}
		world.setBlockState(pos, state, Block.NOTIFY_ALL);
		return false;
	}
}
