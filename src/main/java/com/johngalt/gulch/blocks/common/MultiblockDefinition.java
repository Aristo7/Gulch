package com.johngalt.gulch.blocks.common;

import net.minecraft.block.Block;

/**
 * Created on 10/2/2014.
 */
public class MultiblockDefinition
{
    public int DX;
    public int DY;
    public int DZ;
    public Block Block;

    public MultiblockDefinition(int dx, int dy, int dz, Block block)
    {
        DX = dx;
        DY = dy;
        DZ = dz;
        Block = block;
    }

    public int TranslateX(int x)
    {
        return x + DX;
    }

    public int TranslateY(int y)
    {
        return y + DY;
    }

    public int TranslateZ(int z)
    {
        return z + DZ;
    }
}
