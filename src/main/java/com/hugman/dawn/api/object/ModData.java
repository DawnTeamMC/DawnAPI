package com.hugman.dawn.api.object;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Bundle;
import com.hugman.dawn.api.creator.Creator;
import com.swordglowsblue.artifice.api.Artifice;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModData {
	private final String modName;
	private final List<Creator> CREATORS = new ArrayList<>();

	public ModData(String modName) {
		this.modName = modName;
	}

	public String getModName() {
		return modName;
	}

	public void addCreator(Creator creator) {
		CREATORS.add(creator);
	}

	public void addBundle(Bundle bundle) {
		CREATORS.add(bundle);
	}

	public void registerCreators() {
		CREATORS.forEach(creator -> creator.register(this));
		Dawn.MOD_DATA_LIST.add(this);
	}

	@Environment(EnvType.CLIENT)
	public void registerCreatorsClient() {
		Artifice.registerAssetPack(id("vanilla"), rp -> {
			this.CREATORS.forEach(creator -> creator.clientRegister(this, rp));
			rp.setDisplayName(this.modName + " - Vanilla");
			rp.setDescription("Vanilla resource pack for the " + this.modName + " mod");
			rp.setVisible();
			if(Dawn.CONFIG.debug.outputGeneratedResources) {
				try {
					rp.dumpResources("debug/generated/resources/" + id("vanilla"), "assets");
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void registerCreatorsServer(boolean isDedicated) {
		Artifice.registerDataPack(id("vanilla"), dp -> {
			this.CREATORS.forEach(creator -> creator.serverRegister(this, dp, isDedicated));
			dp.setDisplayName(this.modName + " - Vanilla");
			dp.setDescription("Vanilla data pack for the " + this.modName + " mod");
			if(Dawn.CONFIG.debug.outputGeneratedResources) {

				try {
					dp.dumpResources("debug/generated/resources/" + id("vanilla"), "data");
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Identifier id(String s) {
		return new Identifier(this.modName, s);
	}
}
