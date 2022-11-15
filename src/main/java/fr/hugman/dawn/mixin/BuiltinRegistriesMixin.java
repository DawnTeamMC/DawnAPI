package fr.hugman.dawn.mixin;

import fr.hugman.dawn.registry.DawnRegistryKeys;
import fr.hugman.dawn.shape.ConfiguredShape;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryBuilder;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// TODO: replace with an access widener
@Mixin(BuiltinRegistries.class)
public class BuiltinRegistriesMixin {
	@Shadow
	@Final
	@Mutable
	private static RegistryBuilder REGISTRY_BUILDER;

	@Inject(method = "<clinit>",
			at = @At(value = "FIELD",
					target = "Lnet/minecraft/util/registry/BuiltinRegistries;REGISTRY_BUILDER:Lnet/minecraft/util/registry/RegistryBuilder;",
					opcode = Opcodes.PUTSTATIC,
					ordinal = 0,
					shift = At.Shift.AFTER))
	private static void addDynamicRegistries(CallbackInfo ci) {
		REGISTRY_BUILDER.addRegistry(DawnRegistryKeys.CONFIGURED_SHAPE, ConfiguredShape::bootstrap);
	}
}
