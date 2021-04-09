package com.hugman.dawn.api.object;

import com.hugman.dawn.api.util.StringUtil;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

public interface RegistrableBlock extends Registrable {
	@Environment(EnvType.CLIENT)
	default void registerResources(Identifier identifier, ArtificeResourcePack.ClientResourcePackBuilder rp) {
		rp.addBlockState(identifier, blockStateBuilder -> blockStateBuilder
				.variant("", variant -> variant
						.model(StringUtil.addToPath("block/", identifier))));
		rp.addBlockModel(identifier, modelBuilder -> modelBuilder
				.parent(new Identifier("block/cube_all"))
				.texture("all", StringUtil.addToPath("block/", identifier)));
		rp.addItemModel(identifier, modelBuilder -> modelBuilder
				.parent(StringUtil.addToPath("block/", identifier)));
	}

	default void registerData(Identifier identifier, ArtificeResourcePack.ServerResourcePackBuilder dp) {
		dp.addLootTable(StringUtil.addToPath("blocks/", identifier), lootTableBuilder -> lootTableBuilder
				.type(new Identifier("block"))
				.pool(pool -> pool
						.rolls(1)
						.entry(entry -> entry
								.type(new Identifier("item"))
								.name(identifier))
						.condition(new Identifier("survives_explosion"), j -> {
						})));
	}
}
