package com.johngalt.gulch.biome;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by on 6/22/2014.
 */
public class GulchBiomes
{
    public static final BiomeGenBase roadBiome     = new GulchRoadBiome(66);
    public static final BiomeGenBase buildingBiome = new GulchBuildingBiome(76);

    public static void registerBiomes()
    {
        // not sure if anything is needed here
    }
}
