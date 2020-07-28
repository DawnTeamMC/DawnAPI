package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.StringUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
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
	protected final boolean noItem;
	protected final int cookTime;
	protected final float compostingChance;
	protected final Block copiedBlock;

	private BlockCreator(ModData modData, String name, Block baseBlock, Render render, ItemGroup itemGroup, int flammabilityBurn, int flammabilitySpread, boolean noItem, int cookTime, float compostingChance, Block copiedBlock) {
		super(modData, name);
		this.baseBlock = baseBlock;
		this.render = render;
		this.itemGroup = itemGroup;
		this.flammabilityBurn = flammabilityBurn;
		this.flammabilitySpread = flammabilitySpread;
		this.noItem = noItem;
		this.cookTime = cookTime;
		this.compostingChance = compostingChance;
		this.copiedBlock = copiedBlock;
	}

	@Override
	public Block register() {
		value = Registry.register(Registry.BLOCK, modData.id(name), baseBlock);
		CreatorHelper.setFlammability(value, flammabilityBurn, flammabilitySpread);
		if(!noItem) {
			Item item = Registry.register(Registry.ITEM, Registry.BLOCK.getId(value), new BlockItem(value, new Item.Settings().group(itemGroup)));
			((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
		}
		return value;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void clientRegister() {
		RenderLayer renderF;
		if(render == null && copiedBlock != null) {
			renderF = RenderLayers.getBlockLayer(copiedBlock.getDefaultState());
		}
		else if(render == null) {
			renderF = RenderLayer.getSolid();
		}
		else {
			switch(render) {
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
		BlockRenderLayerMap.INSTANCE.putBlock(value, renderF);
	}

	@Override
	public void serverRegister(boolean isDedicated) {
		if(!noItem) {
			int cookTimeF = cookTime;
			float compostingChanceF = compostingChance;
			if(cookTime == 0 && copiedBlock != null) {
				// TODO
				// cookTimeF = FuelRegistry.INSTANCE.get(copiedBlock);
			}
			if(cookTime != 0) {
				FuelRegistry.INSTANCE.add(value, cookTimeF);
			}
			if(compostingChance == 0 && copiedBlock != null) {
				compostingChanceF = CompostingChanceRegistry.INSTANCE.get(copiedBlock);
			}
			if(compostingChance != 0) {
				CompostingChanceRegistry.INSTANCE.add(value, compostingChanceF);
			}
		}
	}

	public static class Builder extends Creator.Builder<Block> {
		protected Render render;

		protected ItemGroup itemGroup;
		protected int flammabilityBurn;
		protected int flammabilitySpread;
		protected boolean noItem;
		protected int cookTime;
		protected float compostingChance;
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
			this.compostingChance = 0;
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
		 * @param prefix      The prefix of the block.
		 * @param getter      The getter to copy properties from. (block class, name suffix)
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

		public Builder setCompostingChance(float compostingChance) {
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

		/**
		 * Copies some properties from a getter. (render layer, item group)
		 */
		public Builder copy(BlockGetter getter) {
			setItemGroup(getter.getItemGroup());
			setRender(getter.getRender());
			return this;
		}

		/**
		 * Copies some properties from a block. (render layer, item group, flammability, cook time, composting chance)
		 */
		public Builder copy(Block copiedBlock) {
			this.copiedBlock = copiedBlock;
			return this;
		}

		/**
		 * Builds the entry and registers the block with all its settings.
		 */
		public BlockCreator build(ModData modData) {
			if(itemGroup == null && copiedBlock != null) {
				this.itemGroup = copiedBlock.asItem().getGroup();
			}
			if(flammabilityBurn == 0 && flammabilitySpread == 0 && copiedBlock != null) {
				this.flammabilityBurn = CreatorHelper.getFlammabilityBurn(copiedBlock);
				this.flammabilitySpread = CreatorHelper.getFlammabilityBurn(copiedBlock);
			}
			return new BlockCreator(modData, this.name, this.value, this.render, this.itemGroup, this.flammabilityBurn, this.flammabilitySpread, this.noItem, this.cookTime, this.compostingChance, this.copiedBlock);
		}

	}

	public enum Render {
		SOLID,
		CUTOUT,
		CUTOUT_MIPPED,
		TRANSLUCENT
	}
}










