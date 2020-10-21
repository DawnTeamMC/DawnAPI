package com.hugman.dawn.api.mixin;

import com.hugman.dawn.api.object.world.gen.tree.ExtendedTreeGeneration;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {
	@Shadow
	private static boolean isDirtOrGrass(TestableWorld world, BlockPos pos) {
		throw new AssertionError();
	}

	@Redirect(method = "generate(Lnet/minecraft/world/ModifiableTestableWorld;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Ljava/util/Set;Ljava/util/Set;Lnet/minecraft/util/math/BlockBox;Lnet/minecraft/world/gen/feature/TreeFeatureConfig;)Z", at = @At(value = "INVOKE", target = "net/minecraft/world/gen/feature/TreeFeature.isDirtOrGrass(Lnet/minecraft/world/TestableWorld;Lnet/minecraft/util/math/BlockPos;)Z"))
	private boolean dawn_allowSandyTreeGeneration(TestableWorld world, BlockPos pos) {
		if(this instanceof ExtendedTreeGeneration) {
			return ((ExtendedTreeGeneration) this).canGenerateOn(world, pos);
		}
		else {
			return isDirtOrGrass(world, pos);
		}
	}
}
