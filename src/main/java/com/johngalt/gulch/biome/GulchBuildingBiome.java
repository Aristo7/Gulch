package com.johngalt.gulch.biome;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by on 6/22/2014.
 */
public class GulchBuildingBiome extends BiomeGenBase
{
    private final WorldGenerator feature = new WorldGenBuildingRoofFeature();

    public GulchBuildingBiome(int biomeID)
    {
        // register
        super(biomeID, true);
    }

    public void decorate(World world, Random rand, int worldX, int worldZ)
    {
        super.decorate(world, rand, worldX, worldZ);
        int decorateOnX = worldX + rand.nextInt(16) + 8;
        int decorateOnZ = worldZ + rand.nextInt(16) + 8;
        int height = world.getHeightValue(decorateOnX, decorateOnZ);

        assert height > 0;

        this.feature.generate(world, rand, decorateOnX, height, decorateOnZ);
    }
}
