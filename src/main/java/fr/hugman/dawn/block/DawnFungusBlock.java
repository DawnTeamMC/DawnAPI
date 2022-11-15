package fr.hugman.dawn.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FungusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.RegistryKeys;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.function.Predicate;

public class DawnFungusBlock extends FungusBlock {
	private final RegistryKey<ConfiguredFeature<?, ?>> featureKey;
	private final Predicate<BlockState> canPlantOn;
	private final Predicate<BlockState> canGrowOn;

	public DawnFungusBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, TagKey<Block> canPlantOn, TagKey<Block> canGrowOn, Settings settings) {
		this(featureKey, s -> s.isIn(canPlantOn), s -> s.isIn(canGrowOn), settings);
	}

	public DawnFungusBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Predicate<BlockState> canPlantOn, Predicate<BlockState> canGrowOn, Settings settings) {
		super(settings, null, null);
		this.featureKey = featureKey;
		this.canPlantOn = canPlantOn;
		this.canGrowOn = canGrowOn;
	}

	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return this.canPlantOn.test(floor);
	}

	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		return this.canGrowOn.test(world.getBlockState(pos.down()));
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		ConfiguredFeature<?, ?> feature = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE_WORLDGEN).get(this.featureKey);
		if(feature != null) feature.generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
	}
}
