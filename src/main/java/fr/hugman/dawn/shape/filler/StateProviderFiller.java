package fr.hugman.dawn.shape.filler;

import com.terraformersmc.terraform.shapes.api.Filler;
import com.terraformersmc.terraform.shapes.api.Position;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class StateProviderFiller implements Filler {
	private final WorldAccess world;
	private final BlockStateProvider provider;
	private final int flags;

	private StateProviderFiller(WorldAccess world, BlockStateProvider provider, int flags) {
		this.world = world;
		this.provider = provider;
		this.flags = flags;
	}

	public static StateProviderFiller of(WorldAccess world, BlockStateProvider provider, int flags) {
		return new StateProviderFiller(world, provider, flags);
	}

	public static StateProviderFiller of(WorldAccess world, BlockStateProvider provider) {
		return new StateProviderFiller(world, provider, 3);
	}

	@Override
	public void accept(Position position) {
		BlockPos pos = position.toBlockPos();
		this.world.setBlockState(pos, this.provider.get(world.getRandom(), pos), this.flags);
	}
}
