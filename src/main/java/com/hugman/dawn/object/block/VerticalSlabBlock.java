package com.hugman.dawn.object.block;

import com.hugman.dawn.object.block.property.VerticalSlabType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class VerticalSlabBlock extends Block implements Waterloggable {
	public static final EnumProperty<VerticalSlabType> TYPE = EnumProperty.of("type", VerticalSlabType.class);
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
	protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);

	public VerticalSlabBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(TYPE, VerticalSlabType.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public boolean hasSidedTransparency(BlockState state) {
		return state.get(TYPE) != VerticalSlabType.DOUBLE;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
		VerticalSlabType verticalslabtype = state.get(TYPE);
		switch(verticalslabtype) {
			case DOUBLE:
				return VoxelShapes.fullCube();
			case SOUTH:
				return SOUTH_SHAPE;
			case EAST:
				return EAST_SHAPE;
			case WEST:
				return WEST_SHAPE;
			default:
				return NORTH_SHAPE;
		}
	}

	public BlockState getPlacementState(ItemPlacementContext context) {
		BlockState iblockstate = context.getWorld().getBlockState(context.getBlockPos());
		if(iblockstate.getBlock() == this) {
			return iblockstate.with(TYPE, VerticalSlabType.DOUBLE).with(WATERLOGGED, Boolean.valueOf(false));
		}
		else {
			FluidState ifluidstate = context.getWorld().getFluidState(context.getBlockPos());
			BlockState iblockstate1 = this.getDefaultState().with(TYPE, VerticalSlabType.NORTH).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
			Direction facing = context.getPlayerFacing();
			Direction face_hit = context.getSide();
			Vec3d vec3d = context.getHitPos();
			double hitX = vec3d.x - context.getBlockPos().getX();
			double hitZ = vec3d.z - context.getBlockPos().getZ();
			if(facing == Direction.NORTH || facing == Direction.SOUTH) {
				if(face_hit == Direction.SOUTH) {
					return iblockstate1.with(TYPE, VerticalSlabType.NORTH);
				}
				else if(face_hit == Direction.NORTH) {
					return iblockstate1.with(TYPE, VerticalSlabType.SOUTH);
				}
				else if(hitZ > 0.5D) {
					return iblockstate1.with(TYPE, VerticalSlabType.SOUTH);
				}
				else {
					return iblockstate1.with(TYPE, VerticalSlabType.NORTH);
				}
			}
			else if(facing == Direction.EAST || facing == Direction.WEST) {
				if(face_hit == Direction.WEST) {
					return iblockstate1.with(TYPE, VerticalSlabType.EAST);
				}
				else if(face_hit == Direction.EAST) {
					return iblockstate1.with(TYPE, VerticalSlabType.WEST);
				}
				else if(hitX > 0.5D) {
					return iblockstate1.with(TYPE, VerticalSlabType.EAST);
				}
				else {
					return iblockstate1.with(TYPE, VerticalSlabType.WEST);
				}
			}
			else {
				return iblockstate1;
			}
		}
	}

	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext context) {
		ItemStack itemstack = context.getStack();
		VerticalSlabType slabtype = state.get(TYPE);
		if(slabtype != VerticalSlabType.DOUBLE && itemstack.getItem() == this.asItem()) {
			if(context.canReplaceExisting()) {
				Vec3d vec3d = context.getHitPos();
				double hitX = vec3d.x - context.getBlockPos().getX();
				double hitZ = vec3d.z - context.getBlockPos().getZ();
				boolean flag1 = hitZ > 0.5D;
				boolean flag2 = hitX > 0.5D;
				Direction enumfacing = context.getSide();
				if(slabtype == VerticalSlabType.NORTH) {
					return enumfacing == Direction.SOUTH || flag1 && enumfacing.getAxis().isHorizontal();
				}
				if(slabtype == VerticalSlabType.SOUTH) {
					return enumfacing == Direction.NORTH || !flag1 && enumfacing.getAxis().isHorizontal();
				}
				if(slabtype == VerticalSlabType.EAST) {
					return enumfacing == Direction.WEST || !flag2 && enumfacing.getAxis().isHorizontal();
				}
				else {
					return enumfacing == Direction.EAST || flag2 && enumfacing.getAxis().isHorizontal();
				}
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

	@Override
	public Fluid tryDrainFluid(WorldAccess worldIn, BlockPos pos, BlockState state) {
		if(state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		}
		else {
			return Fluids.EMPTY;
		}
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : Fluids.EMPTY.getDefaultState();
	}

	@Override
	public boolean canFillWithFluid(BlockView worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return state.get(TYPE) != VerticalSlabType.DOUBLE && !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}

	@Override
	public boolean tryFillWithFluid(WorldAccess worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if(state.get(TYPE) != VerticalSlabType.DOUBLE && !state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if(!worldIn.isClient()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
				worldIn.getFluidTickScheduler().schedule(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
			}
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState stateIn, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
		if(stateIn.get(WATERLOGGED)) {
			worldIn.getFluidTickScheduler().schedule(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		return stateIn;
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView worldIn, BlockPos pos, NavigationType type) {
		switch(type) {
			case LAND:
				return false;
			case WATER:
				return worldIn.getFluidState(pos).isIn(FluidTags.WATER);
			case AIR:
				return false;
			default:
				return false;
		}
	}
}