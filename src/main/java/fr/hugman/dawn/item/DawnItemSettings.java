package fr.hugman.dawn.item;

import net.fabricmc.fabric.api.item.v1.CustomDamageHandler;
import net.fabricmc.fabric.api.item.v1.EquipmentSlotProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.util.Rarity;

/**
 * Dawn's version of {@code Item.Settings}. Adds additional methods and hooks
 * not found in the original class.
 *
 * <p>To use it, simply replace {@code new Item.Settings()} or {@code new FabricItemSettings()} with
 * {@code new DawnItemSettings()}.
 */
public class DawnItemSettings extends FabricItemSettings {
	private int fuelTime;
	private float compostingChance;

	public DawnItemSettings() {
		super();
	}

	// Getters

	public int getFuelTime() {
		return fuelTime;
	}

	public float getCompostingChance() {
		return compostingChance;
	}

	// New methods

	/**
	 * Sets the fuel time of this item. This is used by furnace-like blocks.
	 *
	 * @see AbstractFurnaceBlockEntity#createFuelTimeMap() Vanilla fuel times
	 */
	public DawnItemSettings fuelTime(int fuelTime) {
		this.fuelTime = fuelTime;
		return this;
	}

	/**
	 * Sets the composting chance of this item. This is used by the composter block.
	 * @see ComposterBlock#registerDefaultCompostableItems() Vanilla composting chances
	 */
	public DawnItemSettings compostingChance(float compostingChance) {
		this.compostingChance = compostingChance;
		return this;
	}

	// Overrides of vanilla and Fabric methods

	@Override
	public DawnItemSettings food(FoodComponent foodComponent) {
		super.food(foodComponent);
		return this;
	}

	@Override
	public DawnItemSettings maxCount(int maxCount) {
		super.maxCount(maxCount);
		return this;
	}

	@Override
	public DawnItemSettings maxDamageIfAbsent(int maxDamage) {
		super.maxDamageIfAbsent(maxDamage);
		return this;
	}

	@Override
	public DawnItemSettings maxDamage(int maxDamage) {
		super.maxDamage(maxDamage);
		return this;
	}

	@Override
	public DawnItemSettings recipeRemainder(Item recipeRemainder) {
		super.recipeRemainder(recipeRemainder);
		return this;
	}

	@Override
	public DawnItemSettings rarity(Rarity rarity) {
		super.rarity(rarity);
		return this;
	}

	@Override
	public DawnItemSettings fireproof() {
		super.fireproof();
		return this;
	}

	@Override
	public DawnItemSettings equipmentSlot(EquipmentSlotProvider equipmentSlotProvider) {
		super.equipmentSlot(equipmentSlotProvider);
		return this;
	}

	@Override
	public DawnItemSettings customDamage(CustomDamageHandler handler) {
		super.customDamage(handler);
		return this;
	}

	@Override
	public DawnItemSettings requires(FeatureFlag... features) {
		super.requires(features);
		return this;
	}
}
