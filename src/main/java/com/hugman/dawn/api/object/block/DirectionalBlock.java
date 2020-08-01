package com.hugman.dawn.api.object.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class DirectionalBlock extends FacingBlock {
	/* Extension for internal publicity
	 * + Missing features */
	public DirectionalBlock(AbstractBlock.Settings builder) {
		super(builder);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP));
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
		return state.mirror(mirrorIn);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(FACING, context.getPlayerLookDirection().getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}
