package com.hugman.dawn.mod.object.block;

import com.hugman.dawn.mod.object.entity.FlyingBlockEntity;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;

public class FlyingBlock extends Block {
	public static boolean flyInstantly;

	public FlyingBlock(Settings builder) {
		super(builder);
	}

	public static boolean canFlyThrough(BlockState state) {
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return block instanceof AirBlock || block == Blocks.FIRE || material.isLiquid() || material.isReplaceable();
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		world.getBlockTickScheduler().schedule(pos, this, this.getFlyDelay());
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
		world.getBlockTickScheduler().schedule(pos, this, this.getFlyDelay());
		return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if(canFlyThrough(world.getBlockState(pos.up())) && pos.getX() <= world.getHeight()) {
			FlyingBlockEntity flyingBlockEntity = new FlyingBlockEntity(world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, world.getBlockState(pos));
			this.configureFlyingBlockEntity(flyingBlockEntity);
			world.spawnEntity(flyingBlockEntity);
		}
	}

	protected void configureFlyingBlockEntity(FlyingBlockEntity entity) {
	}

	protected int getFlyDelay() {
		return 2;
	}

	public void onLanding(World world, BlockPos pos, BlockState flyingBlockState, BlockState currentStateInPos, FlyingBlockEntity flyingBlockEntity) {
	}

	public void onDestroyedOnLanding(World world, BlockPos pos, FlyingBlockEntity flyingBlockEntity) {
	}
}
