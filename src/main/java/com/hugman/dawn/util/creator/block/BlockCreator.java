package com.hugman.dawn.util.creator.block;

import com.hugman.dawn.util.StringUtil;
import com.hugman.dawn.util.creator.Creator;
import com.hugman.dawn.util.creator.CreatorHelper;
import com.hugman.dawn.util.pack.ModData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class BlockCreator extends Creator<Block> {
	protected final Block baseBlock;
	protected final Render render;
	protected final ItemGroup itemGroup;
	protected final int flammabilityBurn;
	protected final int flammabilitySpread;
	protected final int cookTime;
	protected final boolean noItem;
	protected final Block copiedBlock;

	private BlockCreator(ModData modData, String name, Block baseBlock, Render render, ItemGroup itemGroup, int flammabilityBurn, int flammabilitySpread, int cookTime, boolean noItem, Block copiedBlock) {
		super(modData, name);
		this.baseBlock = baseBlock;
		this.render = render;
		this.itemGroup = itemGroup;
		this.flammabilityBurn = flammabilityBurn;
		this.flammabilitySpread = flammabilitySpread;
		this.cookTime = cookTime;
		this.noItem = noItem;
		this.copiedBlock = copiedBlock;
	}

	@Override
	public Block register() {
		value = Registry.register(Registry.BLOCK, modData.id(name), baseBlock);
		FlammableBlockRegistry.getDefaultInstance().add(value, flammabilityBurn, flammabilitySpread);
		if(!noItem) {
			Item item = Registry.register(Registry.ITEM, Registry.BLOCK.getId(value), new BlockItem(value, new Item.Settings().group(itemGroup)));
			((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
			FuelRegistry.INSTANCE.add(value, cookTime);
		}
		return value;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void clientRegister() {
		RenderLayer renderLayer;
		if(render == null && copiedBlock != null) {
			renderLayer = RenderLayers.getBlockLayer(copiedBlock.getDefaultState());
		}
		else if(render == null) {
			renderLayer = RenderLayer.getSolid();
		}
		else {
			switch(render) {
				case SOLID:
				default:
					renderLayer = RenderLayer.getSolid();
					break;
				case CUTOUT:
					renderLayer = RenderLayer.getCutout();
					break;
				case CUTOUT_MIPPED:
					renderLayer = RenderLayer.getCutoutMipped();
					break;
				case TRANSLUCENT:
					renderLayer = RenderLayer.getTranslucent();
					break;
			}
		}
		BlockRenderLayerMap.INSTANCE.putBlock(value, renderLayer);
	}

	public enum Render {
		SOLID,
		CUTOUT,
		CUTOUT_MIPPED,
		TRANSLUCENT
	}

	public static class Builder extends Creator.Builder<Block> {
		protected Render render;
		protected ItemGroup itemGroup;
		protected int flammabilityBurn;
		protected int flammabilitySpread;
		protected int cookTime;
		protected boolean noItem;
		protected Block copiedBlock;

		/**
		 * Creates a simple block with an item but no item group, flammability or cook time and is rendered has a solid block.
		 */
		public Builder(String name, Block block) {
			super(name, block);
			this.render = null;
			this.itemGroup = null;
			this.flammabilityBurn = 0;
			this.flammabilitySpread = 0;
			this.cookTime = 0;
			this.noItem = false;
			this.copiedBlock = null;
		}

		/**
		 * Creates a block copying some properties from a getter and a block.
		 *
		 * @param prefix   The prefix of the block.
		 * @param getter   The getter to copy properties from. (block class, name suffix, render layer, item group)
		 * @param settings The block settings.
		 */
		public Builder(String prefix, BlockGetter getter, AbstractBlock.Settings settings) {
			this(StringUtil.fixShapePrefix(prefix, getter) + getter.getSuffix(), getter.getBlock(settings));
			copy(getter);
		}

		/**
		 * Creates a block copying some properties from a getter and a block.
		 *
		 * @param prefix   The prefix of the block.
		 * @param getter   The getter to copy properties from. (block class, name suffix)
		 * @param copiedBlock The block to copy properties from. (render layer, item group, flammability, cook time)
		 */
		public Builder(String prefix, BlockGetter getter, Block copiedBlock) {
			this(prefix, getter, FabricBlockSettings.copyOf(copiedBlock));
			copy(copiedBlock);
		}

		public Builder setRender(Render render) {
			this.render = render;
			return this;
		}

		public Builder setItemGroup(ItemGroup itemGroup) {
			this.itemGroup = itemGroup;
			return this;
		}

		public Builder setFlammability(int flammabilityBurn, int flammabilitySpread) {
			this.flammabilityBurn = flammabilityBurn;
			this.flammabilitySpread = flammabilitySpread;
			return this;
		}

		public Builder setCookTime(int cookTime) {
			this.cookTime = cookTime;
			return this;
		}

		/**
		 * Removes the item form of the block.
		 */
		public Builder noItem() {
			this.noItem = true;
			return this;
		}

		/**
		 * Copies some properties from a getter. (render layer, item group)
		 */
		public Builder copy(BlockGetter getter) {
			setItemGroup(getter.getItemGroup());
			setRender(getter.getRender());
			return this;
		}

		/**
		 * Copies some properties from a block. (render layer, item group, flammability, cook time)
		 */
		public Builder copy(Block copiedBlock) {
			this.copiedBlock = copiedBlock;
			return this;
		}

		/**
		 * Builds the entry and registers the block with all its settings.
		 */
		public BlockCreator build() {
			if(itemGroup == null && copiedBlock != null) {
				this.itemGroup = copiedBlock.asItem().getGroup();
			}
			if(flammabilityBurn == 0 && flammabilitySpread == 0 && copiedBlock != null) {
				this.flammabilityBurn = CreatorHelper.getFlammabilityBurn(copiedBlock);
				this.flammabilitySpread = CreatorHelper.getFlammabilityBurn(copiedBlock);
			}
			if(cookTime == 0 && copiedBlock != null) {
				//this.cookTime = CreatorHelper.getCookTime(copiedBlock);
			}
			return new BlockCreator(this.modData, this.name, this.value, this.render, this.itemGroup, this.flammabilityBurn, this.flammabilitySpread, this.cookTime, this.noItem, this.copiedBlock);
		}
	}
}
