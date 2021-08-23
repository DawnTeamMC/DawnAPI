package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.object.ModData;
import com.hugman.dawn.api.util.BlockTemplate;
import com.hugman.dawn.api.util.StringUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
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

/**
 * A class allowing a block to be created.
 */
public class BlockCreator extends SimpleCreator<Block> {
	private final Builder builder;

	/**
	 * Creates a block.
	 * @param builder the builder used to create the block itself
	 */
	private BlockCreator(Builder builder) {
		super(Registry.BLOCK, builder.name, builder.blockProvider.apply(builder.settings));
		this.builder = builder;
	}

	/**
	 * @return the builder this block was made with
	 */
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
			renderF = switch(this.builder.render) {
				default -> RenderLayer.getSolid();
				case CUTOUT -> RenderLayer.getCutout();
				case CUTOUT_MIPPED -> RenderLayer.getCutoutMipped();
				case TRANSLUCENT -> RenderLayer.getTranslucent();
			};
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

	/**
	 * A render layer a block can use.
	 */
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

		/**
		 * Sets the name of this block.
		 * @param name a string
		 * @return this builder for chaining
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * Sets the name of this block from a block template.
		 * @param name a string
		 * @param getter a block template
		 * @return this builder for chaining
		 */
		public Builder name(String name, BlockTemplate getter) {
			return name(StringUtil.getShapedName(name, getter));
		}

		/**
		 * @return the name of this block
		 */
		public String getName() {
			return name;
		}

		/**
		 * Sets the block provider of this block.
		 * @param blockProvider a block provider
		 * @return this builder for chaining
		 */
		public Builder blockProvider(Function<AbstractBlock.Settings, ? extends Block> blockProvider) {
			this.blockProvider = blockProvider;
			return this;
		}

		/**
		 * Sets the vanilla settings of this block.
		 * @param settings a vanilla settings builder
		 * @return this builder for chaining
		 */
		public Builder settings(AbstractBlock.Settings settings) {
			this.settings = settings;
			return this;
		}

		/**
		 * Sets the render layer this block will use.
		 * @param render a render layer
		 * @return this builder for chaining
		 */
		public Builder render(Render render) {
			this.render = render;
			return this;
		}

		/**
		 * Sets the item group of this block as an item.
		 * <p>Note: This is unnecessary if {@link #noItem()} is set.</p>
		 * @param itemGroup an item group
		 * @return this builder for chaining
		 */
		public Builder itemGroup(ItemGroup itemGroup) {
			this.itemGroup = itemGroup;
			return this;
		}

		/**
		 * Sets the burn and spread chances of this block.
		 * @param flammability a chance between 0 and 100
		 * @return this builder for chaining
		 */
		public Builder flammability(int flammability) {
			this.flammabilityBurn = flammability;
			this.flammabilitySpread = flammability;
			return this;
		}

		/**
		 * Sets the burn and spread chances of this block.
		 * @param burn a chance between 0 and 100
		 * @param spread a chance between 0 and 100
		 * @return this builder for chaining
		 */
		public Builder flammability(int burn, int spread) {
			this.flammabilityBurn = burn;
			this.flammabilitySpread = spread;
			return this;
		}

		/**
		 * Sets the time this block as an item will burn when used as a fuel.
		 * <p>Note: This is unnecessary if {@link #noItem()} is set.</p>
		 * @param cookTime a number of ticks
		 * @return this builder for chaining
		 */
		public Builder cookTime(int cookTime) {
			this.cookTime = cookTime;
			return this;
		}

		/**
		 * Sets the chance of this block as an item to be composted when used on a composter.
		 * <p>Note: This is unnecessary if {@link #noItem()} is set.</p>
		 * @param compostingChance a chance between 0.0F and 1.0F
		 * @return this builder for chaining
		 */
		public Builder compostingChance(float compostingChance) {
			this.compostingChance = compostingChance;
			return this;
		}

		/**
		 * Removes the item form of this block.
		 * @return this builder for chaining
		 */
		public Builder noItem() {
			this.noItem = true;
			return this;
		}

		/**
		 * Sets the name, the render layer and the block provider of this block, as well as the item group of its item form, from a block template.
		 * @param template a block template
		 * @return this builder for chaining
		 */
		public Builder applyTemplate(BlockTemplate template) {
			return name(this.name, template).itemGroup(template.getItemGroup()).render(template.getRender()).blockProvider(template.getBlockProvider());
		}

		/**
		 * Creates a block creator from the properties of this builder.
		 * @throws NullPointerException if either the name, the block provider or the vanilla settings builder is not set
		 * @return the block creator created
		 */
		public BlockCreator build() {
			Objects.requireNonNull(this.name, "Cannot build a block with no name!");
			Objects.requireNonNull(this.blockProvider, "Cannot build a block with no block provider!");
			Objects.requireNonNull(this.settings, "Cannot build a block with no block settings!");
			return new BlockCreator(copy());
		}

		/**
		 * Creates a new builder with the same properties as this builder.
		 * @return the new builder
		 */
		public Builder copy() {
			return new Builder(this.name, this.blockProvider, FabricBlockSettings.copyOf(this.settings), this.render, this.itemGroup, this.flammabilityBurn, this.flammabilitySpread, this.noItem, this.cookTime, this.compostingChance);
		}
	}
}