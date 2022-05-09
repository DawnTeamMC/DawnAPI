package com.hugman.dawn.mod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "dawn")
@Config.Gui.CategoryBackground(category = "features", background = "minecraft:textures/block/light_blue_concrete.png")
public class DawnConfig extends PartitioningSerializer.GlobalData {
	@ConfigEntry.Category("features")
	@ConfigEntry.Gui.TransitiveObject
	public FeaturesCategory features = new FeaturesCategory();

	@Config(name = "features")
	public static class FeaturesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean creativeToolsTab = true;
	}
}
