package com.hugman.dawn.util.creator;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.MaterialColor;
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

	private BlockCreator(String name, Block baseBlock, Render render, ItemGroup itemGroup, int flammabilityBurn, int flammabilitySpread, int cookTime, boolean noItem, Block copiedBlock) {
		super(name);
		this.baseBlock = baseBlock;
		this.render = render;
		if(itemGroup == null && copiedBlock != null) {
			this.itemGroup = copiedBlock.asItem().getGroup();
		}
		else {
			this.itemGroup = itemGroup;
		}
		if(flammabilityBurn == 0 && flammabilitySpread == 0 && copiedBlock != null) {
			this.flammabilityBurn = CreatorHelper.getFlammabilityBurn(copiedBlock);
			this.flammabilitySpread = CreatorHelper.getFlammabilityBurn(copiedBlock);
		}
		else {
			this.flammabilityBurn = flammabilityBurn;
			this.flammabilitySpread = flammabilitySpread;
		}
		if(cookTime == 0 && copiedBlock != null) {
			this.cookTime = CreatorHelper.getCookTime(copiedBlock);
		}
		else {
			this.cookTime = cookTime;
		}
		this.noItem = noItem;
		this.copiedBlock = copiedBlock;
	}

	@Override
	public Block register(CreatorRegister creatorRegister) {
		value = Registry.register(Registry.BLOCK, creatorRegister.id(name), baseBlock);
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
	public void clientRegister(CreatorRegister creatorRegister) {
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

	public static class Builder {
		protected String name;
		protected Block baseBlock;
		protected Render render;
		protected ItemGroup itemGroup;
		protected int flammabilityBurn;
		protected int flammabilitySpread;
		protected int cookTime;
		protected boolean noItem;
		protected Block copiedBlock;

		/**
		 * Creates a simple block with an item but no item group, flammability or cook time and is rendered has a solid block.
		 *
		 * @param name  The name of the block.
		 * @param block The block itself.
		 */
		public Builder(String name, Block block) {
			this.name = name;
			this.baseBlock = block;
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
		 * <p>Template -> (block class, render layer, item group)
		 * <p>Block -> (block settings, flammability, cook time)
		 *
		 * @param name   The name of the block.
		 * @param getter The getter to copy properties from.
		 */
		public Builder(String name, BlockGetter getter, FabricBlockSettings settings) {
			this(name, getter.getBlock(settings));
		}

		/**
		 * Creates a block copying some properties from a getter and a block.
		 * <p>Template -> (block class, render layer, item group)
		 * <p>Block -> (block settings, flammability, cook time)</p>
		 *
		 * @param name        The name of the block.
		 * @param getter      The getter to copy properties from.
		 * @param copiedBlock The block to copy properties from.
		 */
		public Builder(String name, BlockGetter getter, Block copiedBlock) {
			this(name, getter, FabricBlockSettings.copyOf(copiedBlock));
			this.copiedBlock = copiedBlock;
		}

		/**
		 * Creates a block copying some properties from a getter and a block. This constructor also allows for a custom material color.
		 * <p>Template -> (block class, render layer, item group)
		 * <p>Block -> (block settings, flammability, cook time)
		 *
		 * @param name        The name of the block.
		 * @param getter      The getter to copy properties from.
		 * @param copiedBlock The block to copy properties from.
		 * @param color       The material color of the block.
		 */
		public Builder(String name, BlockGetter getter, Block copiedBlock, MaterialColor color) {
			this(name, getter, FabricBlockSettings.copyOf(copiedBlock).materialColor(color));
			this.copiedBlock = copiedBlock;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public String getName() {
			return name;
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
		 * Copies some properties from a block. (render layer, item group, flammability, cook time)
		 *
		 * @param copiedBlock The block to copy properties from.
		 */
		public Builder copy(Block copiedBlock) {
			this.copiedBlock = copiedBlock;
			return this;
		}

		/**
		 * Builds the entry and registers the block with all its settings.
		 */
		public BlockCreator build() {
			return new BlockCreator(this.name, this.baseBlock, this.render, this.itemGroup, this.flammabilityBurn, this.flammabilitySpread, this.cookTime, this.noItem, this.copiedBlock);
		}
	}
}
