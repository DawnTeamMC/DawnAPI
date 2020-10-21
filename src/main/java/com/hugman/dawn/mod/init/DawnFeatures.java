package com.hugman.dawn.mod.init;

import com.hugman.dawn.api.creator.FeatureCreator;
import com.hugman.dawn.api.object.world.gen.tree.SandyTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class DawnFeatures extends DawnPack {
	public static void init() {

	}

	public static final Feature<TreeFeatureConfig> SANDY_TREE = register(new FeatureCreator.Builder<>("sandy_tree", new SandyTreeFeature(TreeFeatureConfig.CODEC)));
}
