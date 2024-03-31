package fr.hugman.dawn.client;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ClientRegistrar {
    public static void add(TerraformBoatType boatType) {
        var opt = TerraformBoatTypeRegistry.INSTANCE.getKey(boatType);
        if (opt.isEmpty())
            throw new RuntimeException("Failed to retrieve boat type " + boatType + " on client for model layer registering.");
        else TerraformBoatClientHelper.registerModelLayers(opt.get().getValue(), boatType.isRaft());
    }

    public static void add(TerraformBoatType... boatTypes) {
        for (TerraformBoatType boatType : boatTypes) {
            add(boatType);
        }
    }
}
