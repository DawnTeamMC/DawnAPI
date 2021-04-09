package com.hugman.dawn.api.creator.bundle.block;

import com.hugman.dawn.api.creator.BlockCreator;
import com.hugman.dawn.api.creator.Bundle;
import com.hugman.dawn.api.object.ModData;
import com.hugman.dawn.api.util.BlockSettings;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.util.Identifier;

public class PlantBundle extends Bundle {
	private final BlockCreator plant;
	private final BlockCreator pottedPlant;

	public PlantBundle(BlockCreator.Builder builder) {
		this.plant = put(builder.build());
		this.pottedPlant = put(builder.id("potted_" + builder.getName()).block(settings -> new FlowerPotBlock(plant.getBlock(), settings)).settings(BlockSettings.POTTED_PLANT.luminance(getPlant().getDefaultState().getLuminance())).render(BlockCreator.Render.CUTOUT).noItem().build());
	}

	@Override
	public void clientRegister(ModData modData, ArtificeResourcePack.ClientResourcePackBuilder rp) {
		super.clientRegister(modData, rp);
		String namespace = modData.getModName();
		String plantName = plant.getBuilder().getName();
		String pottedPlantName = pottedPlant.getBuilder().getName();
		rp.addItemModel(new Identifier(namespace, plantName), modelBuilder -> modelBuilder
				.parent(new Identifier("item/generated"))
				.texture("layer0", new Identifier(namespace, "block/" + plantName)));
		rp.addBlockState(new Identifier(namespace, plantName), state -> state
				.variant("", variant -> variant
						.model(new Identifier(modData.getModName(), "block/" + plantName))));
		rp.addBlockModel(new Identifier(namespace, plantName), modelBuilder -> modelBuilder
				.parent(new Identifier("block/cross"))
				.texture("cross", new Identifier(namespace, "block/" + plantName)));
		rp.addBlockState(new Identifier(namespace, pottedPlantName), state -> state
				.variant("", variant -> variant
						.model(new Identifier(modData.getModName(), "block/" + pottedPlantName))));
		rp.addBlockModel(new Identifier(namespace, pottedPlantName), modelBuilder -> modelBuilder
				.parent(new Identifier("block/flower_pot_cross"))
				.texture("plant", new Identifier(namespace, "block/" + plantName)));
	}

	public Block getPlant() {
		return this.plant.getBlock();
	}

	public Block getPottedPlant() {
		return this.pottedPlant.getBlock();
	}
}