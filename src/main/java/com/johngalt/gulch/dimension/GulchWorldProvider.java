package com.johngalt.gulch.dimension;

import com.johngalt.gulch.GulchMod;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * Created by on 6/16/2014.
 */

public class GulchWorldProvider extends WorldProvider
{
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.desertHills, 0.1F);
        this.dimensionId = GulchMod.gulchDimension;
    }

    public IChunkProvider createChunkGenerator()
    {
        return new GulchChunkProvider(worldObj, worldObj.getSeed());
    }

    @Override
    public String getDimensionName()
    {
        return "Gulch";
    }
}
