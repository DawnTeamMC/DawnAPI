package com.hugman.dawn.mod.init.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;

@Config(name = "dawn")
@Config.Gui.CategoryBackground(category = "features", background = "minecraft:textures/block/green_concrete.png")
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
		public boolean creativeToolsTab = true;
		public boolean healthCommand = true;
		public boolean motionCommand = true;
		public boolean foodbarCommand = true;
	}

	@Config(name = "debug")
	public static class DebugCategory implements ConfigData {
		@ConfigEntry.Gui.Tooltip
		public boolean generateRegistryEntries = false;
	}
}
