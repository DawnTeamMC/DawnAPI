package fr.hugman.dawn.mixin;

import fr.hugman.dawn.registry.DawnRegistryKeys;
import fr.hugman.dawn.shape.ConfiguredShape;
import net.minecraft.util.registry.RegistryLoader;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(RegistryLoader.class)
public class RegistryLoaderMixin {
	@Shadow
	@Final
	@Mutable
	public static List<RegistryLoader.Entry<?>> DYNAMIC_REGISTRIES;

	@Inject(method = "<clinit>",
			at = @At(value = "FIELD",
					target = "Lnet/minecraft/util/registry/RegistryLoader;DYNAMIC_REGISTRIES:Ljava/util/List;",
					opcode = Opcodes.PUTSTATIC,
					ordinal = 0,
					shift = At.Shift.AFTER))
	private static void addDynamicRegistries(CallbackInfo ci) {
		DYNAMIC_REGISTRIES = new ArrayList<>(DYNAMIC_REGISTRIES);
		DYNAMIC_REGISTRIES.add(new RegistryLoader.Entry<>(DawnRegistryKeys.CONFIGURED_SHAPE, ConfiguredShape.CODEC));
	}
}
