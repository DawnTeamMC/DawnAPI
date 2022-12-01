package fr.hugman.dawn.block.sapling;

import fr.hugman.dawn.DawnFactory;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

/**
 * {@link SaplingGenerator} that will simply generate a single configured feature.
 */
public class SingleSaplingGenerator extends SaplingGenerator {
	public final RegistryKey<ConfiguredFeature<?, ?>> registryKey;

	public SingleSaplingGenerator(Identifier id) {
		this.registryKey = DawnFactory.configuredFeature(id);
	}

	@Nullable
	@Override
	protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
		return registryKey;
	}
}