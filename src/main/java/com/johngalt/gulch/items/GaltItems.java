package com.johngalt.gulch.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/13/2014.
 */
public class GaltItems
{

    public static ItemBlasterRifle BlasterRifle;
    public static ItemBlasterAmmo BlasterAmmo;

    public static Item simpleItem;

    public static void init()
    {
        BlasterRifle = new ItemBlasterRifle();
        BlasterAmmo = new ItemBlasterAmmo();
        simpleItem = new GaltSimpleItem();
    }

    public static void register(GaltCommonItem item)
    {
        GameRegistry.registerItem(item, item.getUnwrappedUnlocalizedName());
    }


    public static void RegisterItems()
    {
        GameRegistry.registerItem(BlasterRifle, "Blaster Rifle");
        GameRegistry.registerItem(BlasterAmmo, "Blaster Ammo");

    }
}
