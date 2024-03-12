package fr.hugman.dawn.mixin;

import fr.hugman.dawn.block.BoneMealSpreadable;
import net.minecraft.block.Block;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(BoneMealItem.class)
public class BoneMealMixin {
	@Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
	public void dawn$useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		ItemStack stack = context.getStack();

		ArrayList<Block> potentialBlocks = new ArrayList<>();
		for(BlockPos pos2 : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
			Block targetBlock = world.getBlockState(pos2).getBlock();
			if(targetBlock instanceof BoneMealSpreadable spreadable) {
				if(spreadable.canSpreadAt(world, pos)) {
					potentialBlocks.add(targetBlock);
				}
			}
		}

		if(!potentialBlocks.isEmpty()) {
			if(!world.isClient()) {
				Block block = potentialBlocks.get(world.random.nextInt(potentialBlocks.size()));
				world.setBlockState(pos, block.getDefaultState(), Block.NOTIFY_ALL);
				stack.decrement(1);
				world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, pos, 0);
			}
			cir.setReturnValue(ActionResult.success(world.isClient));
		}
	}
}
