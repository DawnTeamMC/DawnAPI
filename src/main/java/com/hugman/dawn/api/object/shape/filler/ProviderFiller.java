package com.hugman.dawn.api.object.shape.filler;

import com.terraformersmc.terraform.shapes.api.Filler;
import com.terraformersmc.terraform.shapes.api.Position;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ProviderFiller implements Filler
{
    private final WorldAccess world;
    private final BlockStateProvider provider;
    private final int flags;

    private ProviderFiller(WorldAccess world, BlockStateProvider provider, int flags) {
        this.world = world;
        this.provider = provider;
        this.flags = flags;
    }

    public static ProviderFiller of(WorldAccess world, BlockStateProvider provider, int flags) {
        return new ProviderFiller(world, provider, flags);
    }

    public static ProviderFiller of(WorldAccess world, BlockStateProvider provider) {
        return new ProviderFiller(world, provider, 3);
    }

    @Override
    public void accept(Position position) {
        BlockPos pos = position.toBlockPos();
        this.world.setBlockState(pos, this.provider.getBlockState(world.getRandom(), pos), this.flags);
    }
}
