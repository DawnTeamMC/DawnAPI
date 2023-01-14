package fr.hugman.dawn.shape.processor;

import com.terraformersmc.terraform.shapes.api.Shape;
import com.terraformersmc.terraform.shapes.api.layer.Layer;
import net.minecraft.util.math.random.Random;

// TODO: JavaDoc
public interface LayerShapeProcessor extends ShapeProcessor {
	@Override
	default Shape process(Shape shape, Random random) {
		return shape.applyLayer(get(random));
	}

	Layer get(Random random);
}
