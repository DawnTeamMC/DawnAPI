package com.hugman.dawn.api.util.debug.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.block.Block;
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
			@SerializedName("can_mob_spawn_in")
			protected boolean canMobSpawnInside;
			@Expose
			protected float slipperiness;

			public Properties(Block block) {
				this.canMobSpawnInside = block.canMobSpawnInside();
				this.slipperiness = block.getSlipperiness();
			}
		}
	}
}
