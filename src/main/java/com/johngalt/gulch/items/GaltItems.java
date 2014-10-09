package com.johngalt.gulch.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created on 6/13/2014.
 */
public class GaltItems
{

    public static ItemBlasterRifle BlasterRifle;
    public static ItemBlasterAmmo BlasterAmmo;
    public static ItemMusket Musket;
    public static ItemMusketShot MusketShot;
    public static ItemWadding Wadding;
    public static ItemIngotLead IngotLead;
    public static ItemPaperCartridge PaperCartridge;
    public static Item simpleItem;
    public static ItemGunPowder GunPowder;
    public static ItemKnife Knife;
    public static ItemWoodMusketShot WoodMusketShot;
    public static ItemMusketShotMold MusketShotMold;

    /**
     * Initiates all the item instances.
     */
    public static void init()
    {
        BlasterRifle = new ItemBlasterRifle();
        BlasterAmmo = new ItemBlasterAmmo();
        simpleItem = new GaltSimpleItem();
        Musket = new ItemMusket();
        MusketShot = new ItemMusketShot();
        Wadding = new ItemWadding();
        IngotLead = new ItemIngotLead();
        PaperCartridge = new ItemPaperCartridge();
        GunPowder = new ItemGunPowder();
        WoodMusketShot = new ItemWoodMusketShot();
        Knife = new ItemKnife();

        MusketShotMold = new ItemMusketShotMold();
    }

    /**
     * Used in common objects to register items
     *
     * @param item The item instance to register.
     */
    public static void register(GaltCommonItem item)
    {
        GameRegistry.registerItem(item, item.getUnwrappedUnlocalizedName());
    }


}
