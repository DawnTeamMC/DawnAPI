package fr.hugman.dawn.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.RootsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.Predicate;

public class DawnRootsBlock extends RootsBlock {
	private final Predicate<BlockState> canPlantOn;

	//TODO: codec and register block type

	public DawnRootsBlock(Predicate<BlockState> canPlantOn, Settings settings) {
		super(settings);
		this.canPlantOn = canPlantOn;
	}

	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return this.canPlantOn.test(floor);
	}
}
