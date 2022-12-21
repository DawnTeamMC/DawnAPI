package fr.hugman.dawn.block;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.SignItem;
import net.minecraft.item.VerticallyAttachableBlockItem;

public record SignBlocks(TerraformSignBlock sign, TerraformWallSignBlock wallSign, TerraformHangingSignBlock hangingSign, TerraformWallHangingSignBlock wallHangingSign, SignItem signItem, HangingSignItem hangingSignItem) {
	public AbstractSignBlock get(boolean hanging, boolean wall) {
		return hanging ? (wall ? wallHangingSign : hangingSign) : (wall ? wallSign : sign);
	}

	public VerticallyAttachableBlockItem item(boolean hanging) {
		if(hanging) {
			return hangingSignItem;
		}
		else {
			return signItem;
		}
	}
}
