package fr.hugman.dawn.world.gen.feature;

import com.mojang.serialization.Codec;
import com.terraformersmc.terraform.shapes.api.Position;
import com.terraformersmc.terraform.shapes.api.Quaternion;
import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.impl.layer.transform.RotateLayer;
import com.terraformersmc.terraform.shapes.impl.layer.transform.TranslateLayer;
import com.terraformersmc.terraform.shapes.impl.validator.AirValidator;
import fr.hugman.dawn.shape.filler.StateProviderFiller;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class ShapeFeature extends Feature<ShapeFeatureConfig> {
	public ShapeFeature(Codec<ShapeFeatureConfig> configCodec) {
		super(configCodec);
	}

	@Override
	public boolean generate(FeatureContext<ShapeFeatureConfig> context) {
		Random random = context.getRandom();
		ShapeFeatureConfig config = context.getConfig();
		StructureWorldAccess world = context.getWorld();
		BlockPos pos = context.getOrigin();

		Shape shape = config.shape().value().get(random);

		shape = shape.applyLayer(new RotateLayer(Quaternion.of(0, 0, 0, 1)))
				.applyLayer(new TranslateLayer(Position.of(pos)));

		if(AirValidator.of((TestableWorld) world).validate(shape)) {
			shape = shape.applyLayer(new TranslateLayer(Position.of(0, config.yOffset().get(random), 0)));
			shape.fill(StateProviderFiller.of(world, config.state()));
			return true;
		}
		return false;
	}
}
