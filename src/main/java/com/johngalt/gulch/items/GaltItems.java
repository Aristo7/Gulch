package com.johngalt.gulch.items;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created on 6/13/2014.
 */
public class GaltItems
{
    public static ItemBlasterRifle BlasterRifle;
    public static ItemBlasterAmmo BlasterAmmo;

    public static void InitializeItems()
    {
        BlasterRifle = new ItemBlasterRifle();
        BlasterAmmo = new ItemBlasterAmmo();

    }


    public static void RegisterItems()
    {
        GameRegistry.registerItem(BlasterRifle, "Blaster Rifle");
        GameRegistry.registerItem(BlasterAmmo, "Blaster Ammo");

    }
}
