package com.johngalt.gulch;

import com.johngalt.gulch.items.GaltItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created on 6/13/2014.
 */
@Mod(modid = References.MODID, version = References.VERSION, name = References.MODNAME)
public class GulchMod
{
    @SidedProxy(clientSide = References.CLIENTPROXYLOCATION, serverSide = References.COMMONPROXYLOCATION)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public static void load(FMLPreInitializationEvent event)
    {
        GaltItems.InitializeItems();
        GaltItems.RegisterItems();

    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {

    }
}
