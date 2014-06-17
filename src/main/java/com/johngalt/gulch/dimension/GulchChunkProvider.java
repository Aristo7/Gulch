package com.johngalt.gulch.dimension;

import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderGenerate;

/**
 * Created by on 6/16/2014.
 */
public class GulchChunkProvider extends ChunkProviderGenerate
{
    public GulchChunkProvider(World world, long randomSeed, boolean mapFeaturesEnabled)
    {
        super(world, randomSeed, mapFeaturesEnabled);
    }
}
