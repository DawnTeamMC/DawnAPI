package com.hugman.dawn.mod.object.world_gen.feature;

import com.hugman.dawn.api.object.shape.ConfiguredShape;
import com.hugman.dawn.api.util.DawnCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record ShapeFeatureConfig(ConfiguredShape shape, BlockStateProvider state, FloatProvider yOffset) implements FeatureConfig {
	public static final Codec<ShapeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
			DawnCodecs.CONFIGURED_SHAPE.fieldOf("shape").forGetter((config) -> config.shape),
			BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter((config) -> config.state),
			FloatProvider.VALUE_CODEC.fieldOf("y_offset").orElse(ConstantFloatProvider.create(0.0F)).forGetter((config) -> config.yOffset)
	).apply(instance, ShapeFeatureConfig::new));
}