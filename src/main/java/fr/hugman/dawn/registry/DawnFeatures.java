package fr.hugman.dawn.registry;

import fr.hugman.dawn.Registrar;
import fr.hugman.dawn.world.gen.feature.ShapeFeature;
import fr.hugman.dawn.world.gen.feature.ShapeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class DawnFeatures {
	public static final Feature<ShapeFeatureConfig> SHAPE = new ShapeFeature(ShapeFeatureConfig.CODEC);

	public static void init(Registrar r) {
		r.add("processors", SHAPE);
	}
}
