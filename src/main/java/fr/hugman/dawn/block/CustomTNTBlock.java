package fr.hugman.dawn.block;

import fr.hugman.dawn.entity.CustomTNTEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class CustomTNTBlock extends Block {
	public static final BooleanProperty UNSTABLE = Properties.UNSTABLE;
	public final int fuse;
	public final float strength;

	public CustomTNTBlock(Settings builder, int fuseIn, float strengthIn) {
		super(builder);
		fuse = fuseIn;
		strength = strengthIn;
		this.setDefaultState(this.getDefaultState().with(UNSTABLE, false));
	}

	public CustomTNTBlock(Settings builder, float multiplier) {
		super(builder);
		fuse = Math.round(80.0F * (multiplier * 0.75F));
		strength = 4.0F * multiplier;
		this.setDefaultState(this.getDefaultState().with(UNSTABLE, false));
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		if(!oldState.isOf(state.getBlock())) {
			if(world.isReceivingRedstonePower(pos)) {
				primeTnt(world, pos);
				world.removeBlock(pos, false);
			}

		}
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
		if(world.isReceivingRedstonePower(pos)) {
			primeTnt(world, pos);
			world.removeBlock(pos, false);
		}

	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if(!world.isClient() && !player.isCreative() && state.get(UNSTABLE)) {
			primeTnt(world, pos, player);
		}
		super.onBreak(world, pos, state, player);
	}

	@Override
	public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
		if(!world.isClient) {
			CustomTNTEntity tntEntity = new CustomTNTEntity(world, (float) pos.getX() + 0.5F, pos.getY(), (float) pos.getZ() + 0.5F, this.getDefaultState(), fuse, strength, explosion.getCausingEntity());
			tntEntity.setFuse((short) (world.random.nextInt(tntEntity.getFuse() / 4) + tntEntity.getFuse() / 8));
			world.spawnEntity(tntEntity);
		}
	}

	public void primeTnt(World world, BlockPos pos) {
		primeTnt(world, pos, null);
	}

	private void primeTnt(World world, BlockPos pos, LivingEntity igniter) {
		if(!world.isClient) {
			CustomTNTEntity tntEntity = new CustomTNTEntity(world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, this.getDefaultState(), fuse, strength, igniter);
			world.spawnEntity(tntEntity);
			world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult result) {
		ItemStack itemstack = player.getStackInHand(hand);
		Item item = itemstack.getItem();
		if(item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
			return super.onUse(state, world, pos, player, hand, result);
		}
		else {
			primeTnt(world, pos, player);
			world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
			if(!player.isCreative()) {
				if(item == Items.FLINT_AND_STEEL) {
					itemstack.damage(1, player, (entity) -> {
						entity.sendToolBreakStatus(hand);
					});
				}
				else {
					itemstack.decrement(1);
				}
			}
			return ActionResult.SUCCESS;
		}
	}

	@Override
	public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
		if(!world.isClient) {
			Entity entity = projectile.getOwner();
			if(projectile.isOnFire()) {
				BlockPos blockPos = hit.getBlockPos();
				primeTnt(world, blockPos, entity instanceof LivingEntity ? (LivingEntity) entity : null);
				world.removeBlock(blockPos, false);
			}
		}

	}

	@Override
	public boolean shouldDropItemsOnExplosion(Explosion explosion) {
		return false;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(UNSTABLE);
	}
}
