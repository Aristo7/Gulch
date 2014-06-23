package com.johngalt.gulch.biome;

import com.johngalt.gulch.blocks.GaltBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by on 6/22/2014.
 */
public class WorldGenRoadFeature extends WorldGenerator
{
    public WorldGenRoadFeature()
    {
        super();
    }

    @Override
    public boolean generate(World world, Random rand, int worldX, int worldY, int worldZ)
    {
        world.setBlock(worldX, worldY, worldZ, GaltBlocks.testBlock);
        world.setBlock(worldX, worldY + 1, worldZ, GaltBlocks.testBlock);
        world.setBlock(worldX, worldY + 2, worldZ, GaltBlocks.roadBlock);

        return true;
    }
}
