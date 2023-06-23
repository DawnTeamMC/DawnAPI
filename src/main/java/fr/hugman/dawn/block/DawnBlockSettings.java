package fr.hugman.dawn.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * Dawn's version of {@code Block.Settings}. Adds additional methods and hooks
 * not found in the original class, on top of {@code FabricBlockSettings}.
 *
 * <p>Make note that this behaves slightly different from the
 * vanilla counterpart, copying some settings that vanilla or Fabric does not.
 *
 * <p>To use it, simply replace {@code Block.Settings.of()} or {@code FabricBlockSettings.of()} with
 * {@code DawnBlockSettings.of()}.
 */
public class DawnBlockSettings extends FabricBlockSettings {
	private int flameBurn;
	private int flameSpread;
	@Nullable
	private Block stripInto;
	@Nullable
	private Item.Settings itemSettings;

	protected DawnBlockSettings() {
		super();
	}

	protected DawnBlockSettings(AbstractBlock.Settings settings) {
		super(settings);
		if(settings instanceof DawnBlockSettings dawnSettings) {
			this.flameBurn = dawnSettings.flameBurn;
			this.flameSpread = dawnSettings.flameSpread;
			this.stripInto = dawnSettings.stripInto;
			this.itemSettings = dawnSettings.itemSettings;
		}
	}

	// Getters

	public int getFlameBurn() {
		return this.flameBurn;
	}

	public int getFlameSpread() {
		return this.flameSpread;
	}

	@Nullable
	public Block getStripInto() {
		return this.stripInto;
	}

	@Nullable
	public Item.Settings getItemSettings() {
		return this.itemSettings;
	}

	// Factory methods

	public static DawnBlockSettings create() {
		return new DawnBlockSettings();
	}

	public static DawnBlockSettings copy(AbstractBlock block) {
		return copyOf(((AbstractBlockAccessor) block).getSettings());
	}

	public static DawnBlockSettings copyOf(AbstractBlock block) {
		return copy(block);
	}

	public static DawnBlockSettings copyOf(AbstractBlock.Settings settings) {
		return new DawnBlockSettings(settings);
	}

	// New methods

	/**
	 * Makes the block burnable, sets the burning and spreading chances of this block.
	 *
	 * @see FireBlock#registerDefaultFlammables() Vanilla flammability values
	 */
	public DawnBlockSettings burnable(int burn, int spread) {
		this.flameBurn = burn;
		this.flameSpread = spread;
		this.burnable();
		return this;
	}

	/**
	 * Sets the block that this block will be stripped into when using an axe.
	 * @see AxeItem Vanilla axe stripping values
	 */
	public DawnBlockSettings stripsInto(Block block) {
		this.stripInto = block;
		return this;
	}

	/**
	 * Provides item settings for a {@code BlockItem} to be registered with this block.
	 * <p>The item will be registered with the same identifier as the block.
	 */
	public DawnBlockSettings item(Item.Settings itemSettings) {
		this.itemSettings = itemSettings;
		return this;
	}

	/**
	 * Provides empty item settings for a {@code BlockItem} to be registered with this block.
	 * <p>The item will be registered with the same identifier as the block.
	 */
	public DawnBlockSettings item() {
		return item(new Item.Settings());
	}

	// Overrides of vanilla and Fabric methods

	@Override
	public DawnBlockSettings noCollision() {
		super.noCollision();
		return this;
	}

	@Override
	public DawnBlockSettings nonOpaque() {
		super.nonOpaque();
		return this;
	}

	@Override
	public DawnBlockSettings slipperiness(float value) {
		super.slipperiness(value);
		return this;
	}

	@Override
	public DawnBlockSettings velocityMultiplier(float velocityMultiplier) {
		super.velocityMultiplier(velocityMultiplier);
		return this;
	}

	@Override
	public DawnBlockSettings jumpVelocityMultiplier(float jumpVelocityMultiplier) {
		super.jumpVelocityMultiplier(jumpVelocityMultiplier);
		return this;
	}

	@Override
	public DawnBlockSettings sounds(BlockSoundGroup group) {
		super.sounds(group);
		return this;
	}

	@Override
	public DawnBlockSettings luminance(ToIntFunction<BlockState> luminanceFunction) {
		super.luminance(luminanceFunction);
		return this;
	}

	@Override
	public DawnBlockSettings strength(float hardness, float resistance) {
		super.strength(hardness, resistance);
		return this;
	}

	@Override
	public DawnBlockSettings breakInstantly() {
		super.breakInstantly();
		return this;
	}

	public DawnBlockSettings strength(float strength) {
		super.strength(strength);
		return this;
	}

	@Override
	public DawnBlockSettings ticksRandomly() {
		super.ticksRandomly();
		return this;
	}

	@Override
	public DawnBlockSettings dynamicBounds() {
		super.dynamicBounds();
		return this;
	}

	/**
	 * @deprecated this evaluates the loot table (and therefore item registries) instantly, which is not possible in Dawn Team mods because we do not register anything at the same time as construction.
	 */
	@Override
	@Deprecated
	public DawnBlockSettings dropsLike(Block block) {
		super.dropsLike(block);
		return this;
	}

	@Override
	public DawnBlockSettings air() {
		super.air();
		return this;
	}

	@Override
	public DawnBlockSettings allowsSpawning(AbstractBlock.TypedContextPredicate<EntityType<?>> predicate) {
		super.allowsSpawning(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings solidBlock(AbstractBlock.ContextPredicate predicate) {
		super.solidBlock(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings suffocates(AbstractBlock.ContextPredicate predicate) {
		super.suffocates(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings blockVision(AbstractBlock.ContextPredicate predicate) {
		super.blockVision(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings postProcess(AbstractBlock.ContextPredicate predicate) {
		super.postProcess(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings emissiveLighting(AbstractBlock.ContextPredicate predicate) {
		super.emissiveLighting(predicate);
		return this;
	}

	@Override
	public DawnBlockSettings dropsNothing() {
		super.dropsNothing();
		return this;
	}

	@Override
	public DawnBlockSettings offset(AbstractBlock.OffsetType offsetType) {
		super.offset(offsetType);
		return this;
	}

	@Override
	public DawnBlockSettings noBlockBreakParticles() {
		super.noBlockBreakParticles();
		return this;
	}

	@Override
	public DawnBlockSettings requires(FeatureFlag... features) {
		super.requires(features);
		return this;
	}

	@Override
	public DawnBlockSettings mapColor(Function<BlockState, MapColor> mapColorProvider) {
		super.mapColor(mapColorProvider);
		return this;
	}

	//TODO: what is this?
	@Override
	public DawnBlockSettings burnable() {
		super.burnable();
		return this;
	}

	@Override
	public DawnBlockSettings liquid() {
		super.liquid();
		return this;
	}

	@Override
	public DawnBlockSettings solid() {
		super.solid();
		return this;
	}

	@Override
	public DawnBlockSettings notSolid() {
		super.notSolid();
		return this;
	}

	@Override
	public DawnBlockSettings pistonBehavior(PistonBehavior pistonBehavior) {
		super.pistonBehavior(pistonBehavior);
		return this;
	}

	@Override
	public DawnBlockSettings instrument(Instrument instrument) {
		super.instrument(instrument);
		return this;
	}

	@Override
	public DawnBlockSettings replaceable() {
		super.replaceable();
		return this;
	}

	@Override
	public DawnBlockSettings luminance(int luminance) {
		super.luminance(luminance);
		return this;
	}

	@Override
	public DawnBlockSettings hardness(float hardness) {
		super.hardness(hardness);
		return this;
	}

	@Override
	public DawnBlockSettings resistance(float resistance) {
		super.resistance(resistance);
		return this;
	}

	@Override
	public DawnBlockSettings drops(Identifier dropTableId) {
		super.drops(dropTableId);
		return this;
	}

	@Override
	public DawnBlockSettings requiresTool() {
		super.requiresTool();
		return this;
	}

	@Override
	public DawnBlockSettings mapColor(MapColor color) {
		super.mapColor(color);
		return this;
	}

	@Override
	public DawnBlockSettings mapColor(DyeColor color) {
		super.mapColor(color);
		return this;
	}

	@Override
	public DawnBlockSettings collidable(boolean collidable) {
		super.collidable(collidable);
		return this;
	}
}
