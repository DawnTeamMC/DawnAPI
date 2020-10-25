package com.hugman.dawn.mod.util.debug.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hugman.dawn.api.mixin.ItemAccessor;
import net.minecraft.block.Material;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemEntryData extends EntryData {
	@Expose
	protected List<ItemData> entries;

	public ItemEntryData(String namespace, Set<Identifier> set) {
		super(namespace, Registry.ITEM);
		this.entries = new ArrayList<>();
		for(Identifier itemID : set) {
			this.entries.add(new ItemData(itemID, Registry.ITEM.get(itemID)));
		}
	}

	public class ItemData {
		@Expose
		protected Identifier name;
		@Expose
		protected Properties properties;

		public ItemData(Identifier ID, Item item) {
			this.name = ID;
			this.properties = new Properties(item);
		}

		public class Properties {
			@Expose
			protected String group;
			@Expose
			protected Rarity rarity;
			@Expose
			protected int maxCount;
			@Expose
			protected int maxDamage;
			@Expose
			protected boolean fireproof;
			@Expose
			protected Identifier recipeRemainder;

			public Properties(Item item) {
				ItemAccessor accessor = (ItemAccessor) item;
				this.group = item.getGroup() != null ? item.getGroup().getName() : null;
				this.rarity = accessor.getRarity();
				this.maxCount = item.getMaxCount();
				this.maxDamage = item.getMaxDamage();
				this.fireproof = item.isFireproof();
				this.recipeRemainder = item.getRecipeRemainder() != null ? Registry.ITEM.getId(item.getRecipeRemainder()) : null;
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
