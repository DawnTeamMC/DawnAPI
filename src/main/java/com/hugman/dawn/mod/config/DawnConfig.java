package com.hugman.dawn.mod.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "dawn")
@Config.Gui.CategoryBackground(category = "features", background = "minecraft:textures/block/light_blue_concrete.png")
@Config.Gui.CategoryBackground(category = "debug", background = "minecraft:textures/block/gray_concrete.png")
public class DawnConfig extends PartitioningSerializer.GlobalData {
	@ConfigEntry.Category("features")
	@ConfigEntry.Gui.TransitiveObject
	public FeaturesCategory features = new FeaturesCategory();

	@ConfigEntry.Category("debug")
	@ConfigEntry.Gui.TransitiveObject
	public DebugCategory debug = new DebugCategory();

	@Config(name = "features")
	public static class FeaturesCategory implements ConfigData {
		@ConfigEntry.Gui.RequiresRestart
		public boolean creativeToolsTab = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean healthCommand = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean foodbarCommand = true;
		@ConfigEntry.Gui.RequiresRestart
		public boolean motionCommand = true;
	}

	@Config(name = "debug")
	public static class DebugCategory implements ConfigData {
		@ConfigEntry.Gui.PrefixText
		@ConfigEntry.Gui.Tooltip
		public boolean writeIds = false;
		@ConfigEntry.Gui.Tooltip
		public boolean expandedInfo = false;
	}
}
