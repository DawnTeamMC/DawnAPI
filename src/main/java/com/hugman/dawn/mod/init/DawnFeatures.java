package com.hugman.dawn.mod.init;

import com.hugman.dawn.api.creator.FeatureCreator;
import com.hugman.dawn.mod.object.command.ExportCommand;
import com.hugman.dawn.mod.object.command.FoodBarCommand;
import com.hugman.dawn.mod.object.command.HealthCommand;
import com.hugman.dawn.mod.object.command.MotionCommand;
import com.hugman.dawn.mod.object.world_gen.feature.ShapeFeature;
import com.hugman.dawn.mod.object.world_gen.feature.ShapeFeatureConfig;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.world.gen.feature.Feature;

public class DawnFeatures extends DawnBundle {
	public static final Feature<ShapeFeatureConfig> SHAPE = add(new FeatureCreator<>("shape", new ShapeFeature(ShapeFeatureConfig.CODEC)));

	public static void init() {}
}
