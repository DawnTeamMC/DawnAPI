package fr.hugman.dawn.entity;

import fr.hugman.dawn.registry.DawnEntities;
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
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CustomTNTEntity extends Entity {
    private static final TrackedData<Integer> FUSE = DataTracker.registerData(CustomTNTEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Float> STRENGTH = DataTracker.registerData(CustomTNTEntity.class, TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<BlockState> BLOCK_STATE = DataTracker.registerData(CustomTNTEntity.class, TrackedDataHandlerRegistry.BLOCK_STATE);

    public static final String FUSE_NBT_KEY = "fuse";
    public static final String STRENGTH_NBT_KEY = "strength";
    private static final String BLOCK_STATE_NBT_KEY = "block_state";

    private static final int DEFAULT_FUSE = 80;
    private static final float DEFAULT_STRENGTH = 4.0F;
    private static final BlockState DEFAULT_STATE = Blocks.TNT.getDefaultState();

    @Nullable
    private LivingEntity causingEntity;

    public CustomTNTEntity(EntityType<? extends CustomTNTEntity> type, World worldIn) {
        super(type, worldIn);
        this.intersectionChecked = true;
    }

    public CustomTNTEntity(World world, double x, double y, double z, BlockState state, int fuse, float strength, LivingEntity igniter) {
        this(DawnEntities.CUSTOM_TNT, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.2D, -Math.cos(d) * 0.02);
        this.setFuse(DEFAULT_FUSE);
        this.setStrength(DEFAULT_STRENGTH);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(FUSE, DEFAULT_FUSE);
        builder.add(STRENGTH, DEFAULT_STRENGTH);
        builder.add(BLOCK_STATE, DEFAULT_STATE);
    }

    @Override
    protected MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
    }

    @Override
    public void tick() {
        if (!this.hasNoGravity()) {
            this.setVelocity(this.getVelocity().add(0.0D, -0.04D, 0.0D));
        }
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98D));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7D, -0.5D, 0.7D));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void explode() {
        this.getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625D), this.getZ(), this.getStrength(), World.ExplosionSourceType.TNT);
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.put(BLOCK_STATE_NBT_KEY, NbtHelper.fromBlockState(this.getBlockState()));
        nbt.putShort(FUSE_NBT_KEY, (short) this.getFuse());
        nbt.putFloat(STRENGTH_NBT_KEY, this.getStrength());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.setFuse(nbt.getShort(FUSE_NBT_KEY));
        this.setStrength(nbt.getFloat(STRENGTH_NBT_KEY));
        if (nbt.contains(BLOCK_STATE_NBT_KEY, NbtElement.COMPOUND_TYPE)) {
            this.setBlockState(NbtHelper.toBlockState(this.getWorld().createCommandRegistryWrapper(RegistryKeys.BLOCK), nbt.getCompound(BLOCK_STATE_NBT_KEY)));
        }
    }


    @Nullable
    public LivingEntity getOwner() {
        return this.causingEntity;
    }

    public void setFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return this.dataTracker.get(FUSE);
    }

    public void setBlockState(BlockState state) {
        this.dataTracker.set(BLOCK_STATE, state);
    }

    public BlockState getBlockState() {
        return this.dataTracker.get(BLOCK_STATE);
    }

    public float getStrength() {
        return this.dataTracker.get(STRENGTH);
    }

    public void setStrength(float strength) {
        this.dataTracker.set(STRENGTH, strength);
    }
}