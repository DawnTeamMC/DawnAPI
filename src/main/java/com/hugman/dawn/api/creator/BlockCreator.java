package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.BlockGetter;
import com.hugman.dawn.api.util.CreatorBuilder;
import com.hugman.dawn.api.util.ModData;
import com.hugman.dawn.api.util.StringUtil;
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

public class BlockCreator extends Creator<Block> {
	protected final Render render;
	protected final ItemGroup itemGroup;
	protected final int flammabilityBurn;
	protected final int flammabilitySpread;
	protected final boolean noItem;
	protected final int cookTime;
	protected final float compostingChance;

	private BlockCreator(ModData modData, String name, Block block, Render render, ItemGroup itemGroup, int flammabilityBurn, int flammabilitySpread, boolean noItem, int cookTime, float compostingChance) {
		super(modData, name, block);
		this.render = render;
		this.itemGroup = itemGroup;
		this.flammabilityBurn = flammabilityBurn;
		this.flammabilitySpread = flammabilitySpread;
		this.noItem = noItem;
		this.cookTime = cookTime;
		this.compostingChance = compostingChance;
	}

	@Override
	public void register() {
		Registry.register(Registry.BLOCK, modData.id(name), value);
		FlammableBlockRegistry.getDefaultInstance().add(value, flammabilityBurn, flammabilitySpread);
		if(!noItem) {
			BlockItem blockItem = Registry.register(Registry.ITEM, Registry.BLOCK.getId(value), new BlockItem(value, new Item.Settings().group(itemGroup)));
			blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void clientRegister() {
		RenderLayer renderF;
		if(render != null) {
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
		else {
			renderF = RenderLayer.getSolid();
		}
		BlockRenderLayerMap.INSTANCE.putBlock(value, renderF);
	}

	@Override
	public void serverRegister(boolean isDedicated) {
		if(!noItem) {
			int cookTimeF = cookTime;
			float compostingChanceF = compostingChance;
			if(cookTime != 0) {
				FuelRegistry.INSTANCE.add(value, cookTimeF);
			}
			if(compostingChance != 0) {
				CompostingChanceRegistry.INSTANCE.add(value, compostingChanceF);
			}
		}
	}

	public static class Builder implements CreatorBuilder<Block> {
		private final String name;
		private final Block block;
		protected Render render;

		protected ItemGroup itemGroup;
		protected int flammabilityBurn;
		protected int flammabilitySpread;
		protected boolean noItem;
		protected int cookTime;
		protected float compostingChance;

		/**
		 * Creates a simple block with an item but no item group, flammability or cook time and is rendered has a solid block.
		 *
		 * @param name      The name of the block.
		 * @param block The block itself.
		 */
		public Builder(String name, Block block) {
			this.name = name;
			this.block = block;
		}

		/**
		 * Creates a block copying some properties from a getter and a block.
		 *
		 * @param prefix   The prefix of the block.
		 * @param getter   The getter to copy properties from. (block class, name suffix, render layer, item group)
		 * @param settings The block settings.
		 */
		public Builder(String prefix, BlockGetter getter, AbstractBlock.Settings settings) {
			this(StringUtil.getShapedName(prefix, getter), getter.getBlock(settings));
			copy(getter);
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

		/**
		 * Copies some properties from a getter. (render layer, item group)
		 */
		public Builder copy(BlockGetter getter) {
			itemGroup(getter.getItemGroup());
			render(getter.getRender());
			return this;
		}

		public BlockCreator build(ModData modData) {
			return new BlockCreator(modData, this.name, this.block, this.render, this.itemGroup, this.flammabilityBurn, this.flammabilitySpread, this.noItem, this.cookTime, this.compostingChance);
		}

	}

	public enum Render {
		SOLID,
		CUTOUT,
		CUTOUT_MIPPED,
		TRANSLUCENT
	}
}










