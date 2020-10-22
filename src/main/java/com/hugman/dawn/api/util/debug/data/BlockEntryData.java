package com.hugman.dawn.api.util.debug.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockSettingsAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlockEntryData extends EntryData {
	@Expose
	protected List<BlockData> entries;

	public BlockEntryData(String namespace, Set<Identifier> set) {
		super(namespace, Registry.BLOCK);
		this.entries = new ArrayList<>();
		for(Identifier blockID : set) {
			this.entries.add(new BlockData(blockID, Registry.BLOCK.get(blockID)));
		}
	}

	public class BlockData {
		@Expose
		protected Identifier name;
		@Expose
		protected Properties properties;

		public BlockData(Identifier ID, Block block) {
			this.name = ID;
			this.properties = new Properties(block);
		}

		public class Properties {
			@Expose
			protected MaterialProperties material;
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
				this.material = new MaterialProperties(settings.getMaterial());
				this.hardness = settings.getHardness();
				this.blastResistance = settings.getResistance();
				this.randomlyTicks = settings.getRandomTicks();
				this.slipperiness = settings.getSlipperiness();
				this.velocityMultiplier = settings.getVelocityMultiplier();
				this.dynamicBounds = settings.getDynamicBounds();
				this.opaque = settings.getOpaque();
				this.isAir = settings.getIsAir();
				this.isToolRequired = settings.isToolRequired();
			}

			public class MaterialProperties {
				@Expose
				@SerializedName("is_liquid")
				protected boolean isLiquid;
				@Expose
				@SerializedName("is_solid")
				protected boolean isSolid;
				@Expose
				@SerializedName("blocks_movement")
				protected boolean blocksMovement;
				@Expose
				@SerializedName("is_burnable")
				protected boolean isBurnable;
				@Expose
				@SerializedName("is_replaceable")
				protected boolean isReplaceable;
				@Expose
				@SerializedName("blocks_light")
				protected boolean blocksLight;
				@Expose
				@SerializedName("piston_behavior")
				protected PistonBehavior pistonBehavior;
				@Expose
				protected int color;

				public MaterialProperties(Material material) {
					this.isLiquid = material.isLiquid();
					this.isSolid = material.isSolid();
					this.blocksMovement = material.blocksMovement();
					this.isBurnable = material.isBurnable();
					this.isReplaceable = material.isReplaceable();
					this.blocksLight = material.blocksLight();
					this.pistonBehavior = material.getPistonBehavior();
					this.color = material.getColor().color;
				}
			}
		}
	}
}
