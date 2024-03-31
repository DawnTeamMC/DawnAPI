package fr.hugman.dawn.debug;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockSettingsAccessor;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

public class BlockData {
    @Expose
    protected Identifier name;
    @Expose
    protected Properties properties;

    public BlockData(Identifier id, Block block) {
        this.name = id;
        this.properties = new Properties(block);
    }

    public static class Properties {
        @Expose
        protected float hardness;
        @Expose
        @SerializedName("blast_resistance")
        protected float blastResistance;
        @Expose
        @SerializedName("randomly_ticks")
        protected boolean randomlyTicks;
        @Expose
        protected float slipperiness;
        @Expose
        @SerializedName("velocity_multiplier")
        protected float velocityMultiplier;
        @Expose
        @SerializedName("dynamic_bounds")
        protected boolean dynamicBounds;
        @Expose
        protected boolean opaque;
        @Expose
        @SerializedName("is_air")
        protected boolean isAir;
        @Expose
        @SerializedName("is_tool_required")
        protected boolean isToolRequired;

        public Properties(Block block) {
            AbstractBlockSettingsAccessor settings = (AbstractBlockSettingsAccessor) ((AbstractBlockAccessor) block).getSettings();
            this.hardness = settings.getHardness();
            this.blastResistance = settings.getResistance();
            this.randomlyTicks = settings.getRandomTicks();
            this.slipperiness = settings.getSlipperiness();
            this.velocityMultiplier = settings.getVelocityMultiplier();
            this.dynamicBounds = settings.getDynamicBounds();
            this.opaque = settings.getOpaque();
            this.isAir = settings.getIsAir();
            this.isToolRequired = settings.isToolRequired();
            this.isToolRequired = settings.isToolRequired();
        }
    }
}