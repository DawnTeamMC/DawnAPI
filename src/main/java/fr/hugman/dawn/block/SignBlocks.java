package fr.hugman.dawn.block;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.item.Item;

public record SignBlocks(TerraformSignBlock sign, TerraformWallSignBlock wallSign, TerraformHangingSignBlock hangingSign, TerraformWallHangingSignBlock wallHangingSign) {
	public AbstractSignBlock get(boolean hanging, boolean wall) {
		return hanging ? (wall ? wallHangingSign : hangingSign) : (wall ? wallSign : sign);
	}

	public Item item(boolean hanging) {
		if(hanging) {
			if(hangingSign.asItem() != null) return hangingSign.asItem();
			else if(wallHangingSign.asItem() != null) return wallHangingSign.asItem();
			else throw new RuntimeException("Failed to retrieve sign item for hanging sign.");
		}
		else {
			if(sign.asItem() != null) return sign.asItem();
			else if(wallSign.asItem() != null) return wallSign.asItem();
			else throw new RuntimeException("Failed to retrieve sign item for sign.");
		}
	}
}
