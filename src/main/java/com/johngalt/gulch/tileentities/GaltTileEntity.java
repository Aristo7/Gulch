package com.johngalt.gulch.tileentities;

import net.minecraft.tileentity.TileEntity;

/**
 * Created on 6/14/2014.
 */
public class GaltTileEntity extends TileEntity
{
    public GaltTileEntity()
    {
        super();
    }

    int tick = 0;

    @Override
    public void updateEntity()
    {
        //Only on the server side do
        if (!worldObj.isRemote)
        {
            tick++;
        }
    }
}
