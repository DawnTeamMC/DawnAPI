package fr.hugman.dawn.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.hugman.dawn.shape.ConfiguredShape;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record ShapeFeatureConfig(RegistryEntry<ConfiguredShape> shape, BlockStateProvider state, FloatProvider yOffset) implements FeatureConfig {
	public static final Codec<ShapeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			ConfiguredShape.REGISTRY_CODEC.fieldOf("shape").forGetter((config) -> config.shape),
			BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter((config) -> config.state),
			FloatProvider.VALUE_CODEC.fieldOf("y_offset").orElse(ConstantFloatProvider.create(0.0F)).forGetter((config) -> config.yOffset)
	).apply(instance, ShapeFeatureConfig::new));
}