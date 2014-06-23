package com.johngalt.gulch.biome;

import com.johngalt.gulch.blocks.GaltBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by on 6/22/2014.
 */
public class WorldGenBuildingRoofFeature extends WorldGenerator
{
    public WorldGenBuildingRoofFeature()
    {
        super();
    }

    @Override
    public boolean generate(World world, Random rand, int worldX, int worldY, int worldZ)
    {
        world.setBlock(worldX, worldY, worldZ, GaltBlocks.testBlock);
        world.setBlock(worldX, worldY + 1, worldZ, GaltBlocks.testBlock);
        world.setBlock(worldX, worldY + 2, worldZ, GaltBlocks.roadBlock);

        world.setBlock(worldX, worldY + 2, worldZ + 1, GaltBlocks.roadBlock);
        world.setBlock(worldX, worldY + 2, worldZ - 1, GaltBlocks.roadBlock);
        world.setBlock(worldX + 1, worldY + 2, worldZ, GaltBlocks.roadBlock);
        world.setBlock(worldX - 1, worldY + 2, worldZ, GaltBlocks.roadBlock);

        return true;
    }
}
