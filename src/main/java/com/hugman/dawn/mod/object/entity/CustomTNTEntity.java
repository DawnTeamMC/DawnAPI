package com.hugman.dawn.mod.object.entity;

import com.hugman.dawn.mod.init.DawnEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class CustomTNTEntity extends Entity {
	private static final TrackedData<Integer> FUSE = DataTracker.registerData(CustomTNTEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Float> STRENGTH = DataTracker.registerData(CustomTNTEntity.class, TrackedDataHandlerRegistry.FLOAT);
	private BlockState state = Blocks.SAND.getDefaultState();
	private int fuse = 80;
	private float strength = 4.0F;
	private LivingEntity causingEntity;

	public CustomTNTEntity(EntityType<? extends CustomTNTEntity> type, World worldIn) {
		super(type, worldIn);
		this.intersectionChecked = true;
	}

	public CustomTNTEntity(World world, double x, double y, double z, BlockState state, int fuse, float strength, LivingEntity igniter) {
		this(DawnEntities.CUSTOM_TNT, world);
		this.state = state;
		this.updatePosition(x, y, z);
		float f = (float) (Math.random() * (double) ((float) Math.PI * 2F));
		this.setVelocity(-((float) Math.sin(f)) * 0.02F, 0.2F, -((float) Math.cos(f)) * 0.02F);
		this.setFuse(fuse);
		this.setStrength(strength);
		this.prevX = x;
		this.prevY = y;
		this.prevZ = z;
		this.causingEntity = igniter;
	}

	@Override
	protected void initDataTracker() {
		this.dataTracker.startTracking(FUSE, fuse);
		this.dataTracker.startTracking(STRENGTH, strength);
	}

	@Override
	protected MoveEffect getMoveEffect() {
		return MoveEffect.NONE;
	}

	@Override
	public boolean collides() {
		return !this.isRemoved();
	}

	@Override
	public void tick() {
		if(!this.hasNoGravity()) {
			this.setVelocity(this.getVelocity().add(0.0D, -0.04D, 0.0D));
		}
		this.move(MovementType.SELF, this.getVelocity());
		this.setVelocity(this.getVelocity().multiply(0.98D));
		if(this.onGround) {
			this.setVelocity(this.getVelocity().multiply(0.7D, -0.5D, 0.7D));
		}
		--this.fuse;
		if(this.fuse <= 0) {
			this.discard();
			if(!this.world.isClient) {
				this.explode();
			}
		}
		else {
			this.updateWaterState();
			if(this.world.isClient) {
				this.world.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	private void explode() {
		this.world.createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), this.strength, Explosion.DestructionType.BREAK);
	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound compound) {
		compound.put("BlockState", NbtHelper.fromBlockState(this.state));
		compound.putShort("Fuse", (short) this.getFuse());
		compound.putFloat("Strength", this.getStrength());
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound compound) {
		this.state = NbtHelper.toBlockState(compound.getCompound("BlockState"));
		if(this.state.getBlock() == Blocks.AIR) {
			this.state = Blocks.TNT.getDefaultState();
		}
		this.setFuse(compound.getShort("Fuse"));
		this.setStrength(compound.getFloat("Strength"));
	}

	public LivingEntity getCausingEntity() {
		return this.causingEntity;
	}

	public int getFuse() {
		return this.fuse;
	}

	public void setFuse(int fuseIn) {
		this.dataTracker.set(FUSE, fuseIn);
		this.fuse = fuseIn;
	}

	public BlockState getBlockState() {
		return this.state;
	}

	public float getStrength() {
		return this.strength;
	}

	public void setStrength(float strengthIn) {
		this.dataTracker.set(STRENGTH, strengthIn);
		this.strength = strengthIn;
	}

	@Override
	public void onTrackedDataSet(TrackedData<?> key) {
		if(FUSE.equals(key)) {
			this.fuse = this.getFuseDataManager();
		}
	}

	public int getFuseDataManager() {
		return this.dataTracker.get(FUSE);
	}

	@Override
	public void populateCrashReport(CrashReportSection category) {
		super.populateCrashReport(category);
		category.add("Imitating BlockState", this.state.toString());
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
		this.setFuse(0);
		this.setStrength(0);
	}

	@Override
	public Packet<?> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this, Block.getRawIdFromState(this.getBlockState()));
	}
}