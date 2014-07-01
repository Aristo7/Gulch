package com.johngalt.gulch.proxy;

import com.johngalt.gulch.tileentities.GaltTileEntity;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import com.johngalt.gulch.tileentities.GaltTileEntityFurnace;
import com.johngalt.gulch.tileentities.GaltTileEntityMachine;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/13/2014.
 */
public class CommonProxy
{
    private List<RegisterEntry> _TilesToRegister = new ArrayList<RegisterEntry>();

    public void registerRenderers()
    {

    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(GaltTileEntity.class, "GaltTileEntity");
        GameRegistry.registerTileEntity(GaltTileEntityContainer.class, "GaltTileEntityContainer");

        for (RegisterEntry entry : _TilesToRegister)
            GameRegistry.registerTileEntity(entry.TheClass, entry.RegisterName);
    }

    public void registerTileEntity(Class<? extends TileEntity> aClass, String simpleName)
    {
        _TilesToRegister.add(new RegisterEntry(aClass, simpleName));
    }

    private class RegisterEntry
    {
        public Class<? extends TileEntity> TheClass;
        public String RegisterName;

        public RegisterEntry(Class<? extends TileEntity> aClass, String simpleName)
        {
            TheClass = aClass;
            RegisterName = simpleName;
        }
    }
}
