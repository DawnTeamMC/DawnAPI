package fr.hugman.dawn.block.sapling;

import fr.hugman.dawn.DawnFactory;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

/**
 * {@link SaplingGenerator} that will randomly generate a tree that can be fancy and/ore can contain beehives according to the oak sapling generator algorithm.
 * <p>Said algorithm proceeds as follows:
 * There is a 1/10 chance to generate a fancy tree, otherwise it will be a normal tree.
 * If the sapling is located near flowers, it will generate the bees variants. Feel free to tweak the chance of actually generate the beehive with these variants within the feature's configuration.
 */
public class OakLikeSaplingGenerator extends SaplingGenerator {
	public final RegistryKey<ConfiguredFeature<?, ?>> regular;
	public final RegistryKey<ConfiguredFeature<?, ?>> bees;
	public final RegistryKey<ConfiguredFeature<?, ?>> fancy;
	public final RegistryKey<ConfiguredFeature<?, ?>> fancyBees;

	/**
	 * <p>Identifiers of the features are:
	 * <ul>
	 *     <li><code>M:tree/X/regular</code></li>
	 *     <li><code>M:tree/X/bees</code></li>
	 *     <li><code>M:tree/X/fancy</code></li>
	 *     <li><code>M:tree/X/fancy_bees</code></li>
	 * </ul>
	 * <p>You will need to have configured features registered at these identifiers.</p>
	 *
	 * @param baseId the base ID (<code>M:X</code>)
	 */
	public static OakLikeSaplingGenerator of(Identifier baseId) {
		String modId = baseId.getNamespace();
		String path = baseId.getPath();
		return OakLikeSaplingGenerator.of(
				Identifier.of(modId, "tree/" + path + "/regular"),
				Identifier.of(modId, "tree/" + path + "/bees"),
				Identifier.of(modId, "tree/" + path + "/fancy"),
				Identifier.of(modId, "tree/" + path + "/fancy_bees"));
	}


	public static OakLikeSaplingGenerator of(Identifier regular, Identifier bees, Identifier fancy, Identifier fancyBees) {
		return new OakLikeSaplingGenerator(
				DawnFactory.configuredFeature(regular),
				DawnFactory.configuredFeature(bees),
				DawnFactory.configuredFeature(fancy),
				DawnFactory.configuredFeature(fancyBees));
	}

	public OakLikeSaplingGenerator(RegistryKey<ConfiguredFeature<?, ?>> regular, RegistryKey<ConfiguredFeature<?, ?>> bees,  RegistryKey<ConfiguredFeature<?, ?>> fancy,  RegistryKey<ConfiguredFeature<?, ?>> fancyBees) {
		this.regular = regular;
		this.bees = bees;
		this.fancy = fancy;
		this.fancyBees = fancyBees;
	}

	@Nullable
	@Override
	protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
		return (random.nextInt(10) == 0) ? (bees ? this.fancyBees : this.fancy) : (bees ? this.bees : this.regular);
	}
}