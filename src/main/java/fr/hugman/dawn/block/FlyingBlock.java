package fr.hugman.dawn.block;

import fr.hugman.dawn.entity.FlyingBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class FlyingBlock extends Block {
	public static boolean flyInstantly;

	public FlyingBlock(Settings builder) {
		super(builder);
	}

	public static boolean canFlyThrough(BlockState state) {
		return state.isAir() || state.isIn(BlockTags.FIRE) || state.isLiquid() || state.isReplaceable();
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		world.scheduleBlockTick(pos, this, this.getFlyDelay());
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
		world.scheduleBlockTick(pos, this, this.getFlyDelay());
		return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if(canFlyThrough(world.getBlockState(pos.up())) && pos.getX() <= world.getHeight()) {
			var entity = FlyingBlockEntity.spawnFromBlock(world, pos, state);
			world.spawnEntity(entity);
		}
	}

	protected int getFlyDelay() {
		return 2;
	}

	public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FlyingBlockEntity flyingBlockEntity) {
	}

	public void onDestroyedOnLanding(World world, BlockPos pos, FlyingBlockEntity flyingBlockEntity) {
	}
}
