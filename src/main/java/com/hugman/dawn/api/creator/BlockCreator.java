package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import com.hugman.dawn.api.util.BlockTemplate;
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

import java.util.Objects;
import java.util.function.Function;

public class BlockCreator extends SimpleCreator<Block> {
	private final Builder builder;

	private BlockCreator(Builder builder) {
		super(Registry.BLOCK, builder.name, builder.blockProvider.apply(builder.settings));
		this.builder = builder;
	}

	public Builder getBuilder() {
		return builder;
	}

	@Override
	public void register(ModData modData) {
		super.register(modData);
		FlammableBlockRegistry.getDefaultInstance().add(this.value, this.builder.flammabilityBurn, this.builder.flammabilitySpread);
		if(!this.builder.noItem) {
			BlockItem blockItem = Registry.register(Registry.ITEM, modData.id(this.builder.name), new BlockItem(this.value, new Item.Settings().group(this.builder.itemGroup)));
			blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void clientRegister(ModData modData) {
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
		BlockRenderLayerMap.INSTANCE.putBlock(this.value, renderF);
	}

	@Override
	public void serverRegister(ModData modData, boolean isDedicatedServer) {
		if(!this.builder.noItem) {
			if(this.builder.cookTime != 0) {
				FuelRegistry.INSTANCE.add(this.value, this.builder.cookTime);
			}
			if(this.builder.compostingChance != 0) {
				CompostingChanceRegistry.INSTANCE.add(this.value, this.builder.compostingChance);
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
		protected String name;
		protected Function<AbstractBlock.Settings, ? extends Block> blockProvider;
		protected AbstractBlock.Settings settings;
		protected Render render;
		protected ItemGroup itemGroup;
		protected int flammabilityBurn;
		protected int flammabilitySpread;
		protected boolean noItem;
		protected int cookTime;
		protected float compostingChance;

		public Builder() {
		}

		public Builder(String name, Function<AbstractBlock.Settings, ? extends Block> blockProvider, AbstractBlock.Settings settings, Render render, ItemGroup itemGroup, int flammabilityBurn, int flammabilitySpread, boolean noItem, int cookTime, float compostingChance) {
			this.name = name;
			this.blockProvider = blockProvider;
			this.settings = settings;
			this.render = render;
			this.itemGroup = itemGroup;
			this.flammabilityBurn = flammabilityBurn;
			this.flammabilitySpread = flammabilitySpread;
			this.noItem = noItem;
			this.cookTime = cookTime;
			this.compostingChance = compostingChance;
		}

		public Builder(String prefix, BlockTemplate template, AbstractBlock.Settings settings) {
			this.name = prefix;
			this.applyTemplate(template);
			this.settings = settings;
		}

		public Builder(String name, Function<AbstractBlock.Settings, ? extends Block> blockProvider, AbstractBlock.Settings settings) {
			this.name = name;
			this.blockProvider = blockProvider;
			this.settings = settings;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder name(String name, BlockTemplate getter) {
			return name(StringUtil.getShapedName(name, getter));
		}

		public String getName() {
			return name;
		}

		public Builder blockProvider(Function<AbstractBlock.Settings, ? extends Block> blockProvider) {
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

		public Builder applyTemplate(BlockTemplate template) {
			return name(this.name, template).itemGroup(template.getItemGroup()).render(template.getRender()).blockProvider(template.getBlockProvider());
		}

		public BlockCreator build() {
			Objects.requireNonNull(this.name, "Cannot build a block with no name!");
			Objects.requireNonNull(this.blockProvider, "Cannot build a block with no block provider!");
			Objects.requireNonNull(this.settings, "Cannot build a block with no block settings!");
			return new BlockCreator(copy());
		}

		public Builder copy() {
			return new Builder(this.name, this.blockProvider, this.settings, this.render, this.itemGroup, this.flammabilityBurn, this.flammabilitySpread, this.noItem, this.cookTime, this.compostingChance);
		}
	}
}