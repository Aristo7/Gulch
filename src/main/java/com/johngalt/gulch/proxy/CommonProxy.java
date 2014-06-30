package com.johngalt.gulch.proxy;

import com.johngalt.gulch.tileentities.GaltTileEntity;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import com.johngalt.gulch.tileentities.GaltTileEntityFurnace;
import com.johngalt.gulch.tileentities.GaltTileEntityMachine;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created on 6/13/2014.
 */
public class CommonProxy
{
    public void registerRenderers()
    {

    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(GaltTileEntity.class, "GaltTileEntity");
        GameRegistry.registerTileEntity(GaltTileEntityContainer.class, "GaltTileEntityContainer");
        GameRegistry.registerTileEntity(GaltTileEntityFurnace.class, "GaltTileEntityFurnace");
    }
}
