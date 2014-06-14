package com.johngalt.gulch.items;

import com.johngalt.gulch.ItemBlasterRifle;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created on 6/13/2014.
 */
public class GaltItems
{
    public static ItemBlasterRifle BlasterRifle;

    public static void InitializeItems()
    {
        BlasterRifle = new ItemBlasterRifle();


    }


    public static void RegisterItems()
    {
        GameRegistry.registerItem(BlasterRifle, "Blaster Rifle");


    }
}
