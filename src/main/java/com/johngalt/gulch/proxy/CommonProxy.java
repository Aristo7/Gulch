package com.johngalt.gulch.proxy;


import com.johngalt.gulch.tileentities.common.GaltTileEntity;
import com.johngalt.gulch.tileentities.common.GaltTileEntityContainer;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/13/2014.
 */
public class CommonProxy
{
    private List<RegisterTileEntry> _TilesToRegister = new ArrayList<RegisterTileEntry>();

    public void RegisterRenderers()
    {

    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(GaltTileEntity.class, "GaltTileEntity");
        GameRegistry.registerTileEntity(GaltTileEntityContainer.class, "GaltTileEntityContainer");

        for (RegisterTileEntry entry : _TilesToRegister)
            GameRegistry.registerTileEntity(entry.TheClass, entry.RegisterName);
    }

    public void registerTileEntity(Class<? extends TileEntity> aClass, String simpleName)
    {
        for (RegisterTileEntry entry : _TilesToRegister)
            if (entry.TheClass == aClass)
                return;

        _TilesToRegister.add(new RegisterTileEntry(aClass, simpleName));
    }

    private class RegisterTileEntry
    {
        public Class<? extends TileEntity> TheClass;
        public String RegisterName;

        public RegisterTileEntry(Class<? extends TileEntity> aClass, String simpleName)
        {
            TheClass = aClass;
            RegisterName = simpleName;
        }
    }
}
