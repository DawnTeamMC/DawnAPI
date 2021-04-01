package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.ModData;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.Objects;
import java.util.function.Function;

public class BlockCreator extends Creator {
	protected Block block;
	private final Builder builder;

	private BlockCreator(Builder builder) {
		this.builder = builder;
		this.block = builder.blockProvider.apply(builder.settings);
	}

	public Block getBlock() {
		return block;
	}

	@Override
	public void register(ModData modData) {
		Registry.register(Registry.BLOCK, modData.id(this.builder.id), this.block);
		FlammableBlockRegistry.getDefaultInstance().add(this.block, this.builder.flammabilityBurn, this.builder.flammabilitySpread);
		if(!this.builder.noItem) {
			BlockItem blockItem = Registry.register(Registry.ITEM, modData.id(this.builder.id), new BlockItem(this.block, new Item.Settings().group(this.builder.itemGroup)));
			blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void clientRegister(ModData modData, ArtificeResourcePack.ClientResourcePackBuilder resourcePackBuilder) {
		RenderLayer renderF;
		if(this.builder.render != null) {
			switch(this.builder.render) {
				case SOLID:
				default:
					renderF = RenderLayer.getSolid();
					break;
				case CUTOUT:
					renderF = RenderLayer.getCutout();
					break;
				case CUTOUT_MIPPED:
					renderF = RenderLayer.getCutoutMipped();
					break;
				case TRANSLUCENT:
					renderF = RenderLayer.getTranslucent();
					break;
			}
		}
		else {
			renderF = RenderLayer.getSolid();
		}
		BlockRenderLayerMap.INSTANCE.putBlock(this.block, renderF);
	}

	@Override
	public void serverRegister(ModData modData, ArtificeResourcePack.ServerResourcePackBuilder dataPackBuilder, boolean isDedicatedServer) {
		if(!this.builder.noItem) {
			if(this.builder.cookTime != 0) {
				FuelRegistry.INSTANCE.add(block, this.builder.cookTime);
			}
			if(this.builder.compostingChance != 0) {
				CompostingChanceRegistry.INSTANCE.add(block, this.builder.compostingChance);
			}
		}
	}

	public enum Render {
		SOLID,
		CUTOUT,
		CUTOUT_MIPPED,
		TRANSLUCENT
	}

	public static class Builder {
		protected AbstractBlock.Settings settings;
		protected Render render;
		protected ItemGroup itemGroup;
		protected int flammabilityBurn;
		protected int flammabilitySpread;
		protected boolean noItem;
		protected int cookTime;
		protected float compostingChance;
		private String id;
		private Function<AbstractBlock.Settings, ? extends Block> blockProvider;

		public Builder() {

		}

		public Builder(String id, Function<AbstractBlock.Settings, ? extends Block> blockProvider, AbstractBlock.Settings settings) {
			this.id(id);
			this.block(blockProvider);
			this.settings(settings);
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public String getId() {
			return id;
		}

		public Builder block(Function<AbstractBlock.Settings, ? extends Block> blockProvider) {
			this.blockProvider = blockProvider;
			return this;
		}

		public Builder settings(AbstractBlock.Settings settings) {
			this.settings = settings;
			return this;
		}

		public Builder render(Render render) {
			this.render = render;
			return this;
		}

		public Builder itemGroup(ItemGroup itemGroup) {
			this.itemGroup = itemGroup;
			return this;
		}

		public Builder flammability(int flammability) {
			this.flammabilityBurn = flammability;
			this.flammabilitySpread = flammability;
			return this;
		}

		public Builder flammability(int burn, int spread) {
			this.flammabilityBurn = burn;
			this.flammabilitySpread = spread;
			return this;
		}

		public Builder cookTime(int cookTime) {
			this.cookTime = cookTime;
			return this;
		}

		public Builder compostingChance(float compostingChance) {
			this.compostingChance = compostingChance;
			return this;
		}

		/**
		 * Removes the item form of the block.
		 */
		public Builder noItem() {
			this.noItem = true;
			return this;
		}

		public BlockCreator build() {
			Objects.requireNonNull(this.id, "Cannot build a block with no name!");
			Objects.requireNonNull(this.blockProvider, "Cannot build a block with no block provider!");
			Objects.requireNonNull(this.settings, "Cannot build a block with no block settings!");
			return new BlockCreator(this);
		}
	}
}










