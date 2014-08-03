package com.johngalt.gulch.lib;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created on 7/8/2014.
 */
public class Position
{
    public int x;
    public int y;
    public int z;

    public Position(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void WriteToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("x", x);
        nbt.setInteger("y", y);
        nbt.setInteger("z", z);
    }

    public static Position GetPositionFromNBT(NBTTagCompound nbt)
    {
        return new Position(nbt.getInteger("x"), nbt.getInteger("y"), nbt.getInteger("z"));
    }
}
