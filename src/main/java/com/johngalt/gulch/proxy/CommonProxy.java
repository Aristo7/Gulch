package com.johngalt.gulch.proxy;

import com.johngalt.gulch.entities.mobs.ExampleMob;
import com.johngalt.gulch.entities.mobs.models.ModelExampleMob;
import com.johngalt.gulch.entities.mobs.render.RenderExampleMob;
import com.johngalt.gulch.tileentities.GaltTileEntity;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import com.johngalt.gulch.tileentities.GaltTileEntityCustRender;
import cpw.mods.fml.client.registry.RenderingRegistry;
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
        RenderingRegistry.registerEntityRenderingHandler(ExampleMob.class, new RenderExampleMob(new ModelExampleMob(), 0.5F));
    }

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(GaltTileEntity.class, "GaltTileEntity");
        GameRegistry.registerTileEntity(GaltTileEntityContainer.class, "GaltTileEntityContainer");
        GameRegistry.registerTileEntity(GaltTileEntityCustRender.class, "GaltTileEntityBellow");

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
