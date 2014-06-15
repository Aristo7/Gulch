package com.johngalt.gulch.tileentities;

import com.johngalt.gulch.GulchMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

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
