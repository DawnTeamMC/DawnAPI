package fr.hugman.dawn.client;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import fr.hugman.dawn.block.SignBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;

@Environment(EnvType.CLIENT)
public class ClientRegistrar {
	public static void add(TerraformBoatType boatType) {
		var opt = TerraformBoatTypeRegistry.INSTANCE.getKey(boatType);
		if(opt.isEmpty()) throw new RuntimeException("Failed to retrieve boat type " + boatType + " on client for model layer registering.");
		else TerraformBoatClientHelper.registerModelLayers(opt.get().getValue(), boatType.isRaft());
	}

	public static void add(TerraformBoatType... boatTypes) {
		for(TerraformBoatType boatType : boatTypes) {
			add(boatType);
		}
	}

	public static void add(AbstractSignBlock sign) {
		if(sign instanceof TerraformSignBlock terraSign) {
			SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, terraSign.getTexture()));
		}
		else if(sign instanceof TerraformHangingSignBlock terraSign) {
			SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, terraSign.getTexture()));
		}
		else {
			throw new IllegalArgumentException("Only Terraform API signs may be registered via this method.");
		}
	}

	public static void add(AbstractSignBlock... signs) {
		for(AbstractSignBlock sign : signs) {
			add(sign);
		}
	}

	public static void add(SignBlocks signs) {
		add(signs.sign(), signs.wallSign(), signs.hangingSign(), signs.wallHangingSign());
	}
}
