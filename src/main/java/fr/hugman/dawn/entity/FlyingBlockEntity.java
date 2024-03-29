package fr.hugman.dawn.entity;

import fr.hugman.dawn.block.FlyingBlock;
import fr.hugman.dawn.registry.DawnEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class FlyingBlockEntity extends Entity {
	protected static final TrackedData<BlockPos> BLOCK_POS = DataTracker.registerData(FlyingBlockEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
	private final float flyHurtAmount;
	public int timeFlying;
	public boolean dropItem;
	public NbtCompound blockEntityData;
	private BlockState state;
	private boolean destroyedOnLanding;
	private boolean hurtEntities;
	private int flyHurtMax;

	public FlyingBlockEntity(EntityType<? extends FlyingBlockEntity> type, World world) {
		super(type, world);
		this.state = Blocks.SAND.getDefaultState();
		this.dropItem = true;
		this.flyHurtMax = 40;
		this.flyHurtAmount = 2.0F;
	}

	public FlyingBlockEntity(World world, double x, double y, double z, BlockState state) {
		this(DawnEntities.FLYING_BLOCK, world);
		this.state = state;
		this.intersectionChecked = true;
		this.updatePosition(x, y + (double) ((1.0F - this.getHeight()) / 2.0F), z);
		this.setVelocity(Vec3d.ZERO);
		this.prevX = x;
		this.prevY = y;
		this.prevZ = z;
		this.setFlyingBlockPos(this.getBlockPos());
	}

	public static FlyingBlockEntity spawnFromBlock(World world, BlockPos pos, BlockState state) {
		FlyingBlockEntity flyingBlockEntity = new FlyingBlockEntity(world, (double) pos.getX() + 0.5, pos.getY(), (double) pos.getZ() + 0.5, state.contains(Properties.WATERLOGGED) ? state.with(Properties.WATERLOGGED, false) : state);
		world.setBlockState(pos, state.getFluidState().getBlockState(), Block.NOTIFY_ALL);
		world.spawnEntity(flyingBlockEntity);
		return flyingBlockEntity;
	}


	@Override
	public boolean isAttackable() {
		return false;
	}

	public void setFlyingBlockPos(BlockPos pos) {
		this.dataTracker.set(BLOCK_POS, pos);
	}

	@Environment(EnvType.CLIENT)
	public BlockPos getFallingBlockPos() {
		return this.dataTracker.get(BLOCK_POS);
	}

	@Override
	protected MoveEffect getMoveEffect() {
		return MoveEffect.NONE;
	}

	@Override
	protected void initDataTracker() {
		this.dataTracker.startTracking(BLOCK_POS, BlockPos.ORIGIN);
	}

	@Override
	public boolean canHit() {
		return !this.isRemoved();
	}

	@Override
	public void tick() {
		if(this.state.isAir()) {
			this.discard();
		}
		else {
			Block block = this.state.getBlock();
			BlockPos blockPos2;
			if(this.timeFlying++ == 0) {
				blockPos2 = this.getBlockPos();
				if(this.getWorld().getBlockState(blockPos2).isOf(block)) {
					this.getWorld().removeBlock(blockPos2, false);
				}
				else if(!this.getWorld().isClient) {
					this.discard();
					return;
				}
			}
			if(!this.hasNoGravity()) {
				this.setVelocity(this.getVelocity().add(0.0D, 0.01D, 0.0D));
			}
			this.move(MovementType.SELF, this.getVelocity());
			if(!this.getWorld().isClient) {
				blockPos2 = this.getBlockPos();
				boolean bl = this.state.getBlock() instanceof ConcretePowderBlock;
				boolean bl2 = bl && this.getWorld().getFluidState(blockPos2).isIn(FluidTags.WATER);
				double d = this.getVelocity().lengthSquared();
				if(bl && d > 1.0D) {
					BlockHitResult blockHitResult = this.getWorld().raycast(new RaycastContext(new Vec3d(this.prevX, this.prevY, this.prevZ), this.getPos(), ShapeType.COLLIDER, FluidHandling.SOURCE_ONLY, this));
					if(blockHitResult.getType() != Type.MISS && this.getWorld().getFluidState(blockHitResult.getBlockPos()).isIn(FluidTags.WATER)) {
						blockPos2 = blockHitResult.getBlockPos();
						bl2 = true;
					}
				}
				if(FlyingBlock.canFlyThrough(this.getWorld().getBlockState(blockPos2.up())) && !bl2) {
					if(!this.getWorld().isClient && (this.timeFlying > 100 && (blockPos2.getY() < 1 || blockPos2.getY() > 256) || this.timeFlying > 600)) {
						if(this.dropItem && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
							this.dropItem(block);
						}
						this.discard();
					}
				}
				else {
					BlockState blockState = this.getWorld().getBlockState(blockPos2);
					this.setVelocity(this.getVelocity().multiply(0.7D, 0.4D, 0.7D));
					if(!blockState.isOf(Blocks.MOVING_PISTON)) {
						this.discard();
						if(!this.destroyedOnLanding) {
							boolean bl3 = blockState.canReplace(new AutomaticItemPlacementContext(this.getWorld(), blockPos2, Direction.UP, ItemStack.EMPTY, Direction.DOWN));
							boolean bl4 = this.state.canPlaceAt(this.getWorld(), blockPos2);
							if(bl3 && bl4) {
								if(this.state.contains(Properties.WATERLOGGED) && this.getWorld().getFluidState(blockPos2).getFluid() == Fluids.WATER) {
									this.state = this.state.with(Properties.WATERLOGGED, Boolean.valueOf(true));
								}
								if(this.getWorld().setBlockState(blockPos2, this.state, 3)) {
									if(block instanceof FlyingBlock) {
										((FlyingBlock) block).onLanding(this.getWorld(), blockPos2, this.state, blockState, this);
									}
									if(this.blockEntityData != null && block instanceof BlockEntityProvider) {
										BlockEntity blockEntity = this.getWorld().getBlockEntity(blockPos2);
										if(blockEntity != null) {
											NbtCompound nbtCompound = blockEntity.createNbt();
											for(String string : this.blockEntityData.getKeys()) {
												NbtElement tag = this.blockEntityData.get(string);
												if(!"x".equals(string) && !"y".equals(string) && !"z".equals(string)) {
													nbtCompound.put(string, tag.copy());
												}
											}
											blockEntity.readNbt(nbtCompound);
											blockEntity.markDirty();
										}
									}
								}
								else if(this.dropItem && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
									this.dropItem(block);
								}
							}
							else if(this.dropItem && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
								this.dropItem(block);
							}
						}
						else if(block instanceof FlyingBlock) {
							((FlyingBlock) block).onDestroyedOnLanding(this.getWorld(), blockPos2, this);
						}
					}
				}
			}
			this.setVelocity(this.getVelocity().multiply(0.98D));
		}
	}

	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
		DamageSource damageSource2;
		if(!this.hurtEntities) {
			return false;
		}
		int i = MathHelper.ceil(fallDistance - 1.0F);
		if(i < 0) {
			return false;
		}
		Predicate<Entity> predicate = EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.and(EntityPredicates.VALID_LIVING_ENTITY);
		if(this.state.getBlock() instanceof LandingBlock landingBlock) {
			damageSource2 = landingBlock.getDamageSource(this);
		}
		else {
			damageSource2 = this.getDamageSources().fallingBlock(this);
		}

		float f = (float) Math.min(MathHelper.floor((float) i * this.flyHurtAmount), this.flyHurtMax);
		this.getWorld().getOtherEntities(this, this.getBoundingBox(), predicate).forEach((entity) -> entity.damage(damageSource2, f));
		boolean bl = this.state.isIn(BlockTags.ANVIL);
		if(bl && this.random.nextFloat() < 0.05f + (float) i * 0.05f) {
			BlockState blockState = AnvilBlock.getLandingState(this.state);
			if(blockState == null) {
				this.destroyedOnLanding = true;
			}
			else {
				this.state = blockState;
			}
		}
		return false;
	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound tag) {
		tag.put("BlockState", NbtHelper.fromBlockState(this.state));
		tag.putInt("Time", this.timeFlying);
		tag.putBoolean("DropItem", this.dropItem);
		tag.putBoolean("HurtEntities", this.hurtEntities);
		tag.putFloat("FlyHurtAmount", this.flyHurtAmount);
		tag.putInt("FlyHurtMax", this.flyHurtMax);
		if(this.blockEntityData != null) {
			tag.put("TileEntityData", this.blockEntityData);
		}
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound tag) {
		this.state = NbtHelper.toBlockState(this.getWorld().createCommandRegistryWrapper(RegistryKeys.BLOCK), tag.getCompound("BlockState"));
		this.timeFlying = tag.getInt("Time");
		if(tag.contains("HurtEntities", 99)) {
			this.hurtEntities = tag.getBoolean("HurtEntities");
			this.flyHurtMax = tag.getInt("FlyHurtMax");
		}
		else if(this.state.isIn(BlockTags.ANVIL)) {
			this.hurtEntities = true;
		}
		if(tag.contains("DropItem", 99)) {
			this.dropItem = tag.getBoolean("DropItem");
		}
		if(tag.contains("TileEntityData", 10)) {
			this.blockEntityData = tag.getCompound("TileEntityData");
		}
		if(this.state.getBlock() instanceof AirBlock) {
			this.state = Blocks.STONE.getDefaultState();
		}
	}

	@Environment(EnvType.CLIENT)
	public World getWorldClient() {
		return this.getWorld();
	}

	public void setHurtEntities(boolean hurtEntitiesIn) {
		this.hurtEntities = hurtEntitiesIn;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean doesRenderOnFire() {
		return false;
	}

	@Override
	public void populateCrashReport(CrashReportSection category) {
		super.populateCrashReport(category);
		category.add("Imitating BlockState", this.state.toString());
	}

	public BlockState getBlockState() {
		return this.state;
	}

	@Override
	public boolean entityDataRequiresOperator() {
		return true;
	}

	@Override
	public void onSpawnPacket(EntitySpawnS2CPacket packet) {
		super.onSpawnPacket(packet);
		this.state = Block.getStateFromRawId(packet.getEntityData());
		this.intersectionChecked = true;
		double d = packet.getX();
		double e = packet.getY();
		double f = packet.getZ();
		this.setPosition(d, e + (double) ((1.0F - this.getHeight()) / 2.0F), f);
		this.setFlyingBlockPos(this.getBlockPos());
	}

	@Override
	public Packet<ClientPlayPacketListener> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this, Block.getRawIdFromState(this.getBlockState()));
	}
}