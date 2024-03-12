package fr.hugman.dawn.item.settings;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;

import java.util.WeakHashMap;

public final class DawnItemInternals {
	private static final WeakHashMap<Item.Settings, ExtraData> extraData = new WeakHashMap<>();

	private DawnItemInternals() {
	}

	public static ExtraData extraData(Item.Settings settings) {
		return extraData.computeIfAbsent(settings, s -> new ExtraData());
	}

	public static void onBuild(Item.Settings settings, Item item) {
		ExtraData data = extraData.get(settings);

		if (data != null) {
			if(data.compostingChance > 0.0f)
				CompostingChanceRegistry.INSTANCE.add(item, data.compostingChance);
			if(data.fuelTime > 0)
				FuelRegistry.INSTANCE.add(item, data.fuelTime);
		}
	}

	public static final class ExtraData {
		private int fuelTime;
		private float compostingChance;

		public void fuelTime(int time) {
			this.fuelTime = time;
		}

		public void compostingChance(float chance) {
			this.compostingChance = chance;
		}
	}
}
