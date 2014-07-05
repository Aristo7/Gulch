package com.johngalt.gulch.tileentities.common;

import net.minecraft.tileentity.TileEntity;

/**
 * Created on 6/14/2014.
 */
public class GaltTileEntity extends TileEntity
{
    protected String _LocalizedName;

    public GaltTileEntity()
    {
        super();
    }

    int tick = 0;

    public void setGuiDisplayName(String displayName)
    {
        _LocalizedName = displayName;
    }

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
