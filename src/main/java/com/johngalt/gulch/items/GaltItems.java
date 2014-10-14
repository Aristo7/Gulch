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

    public static ItemWoodBase WoodMusketShot;
    public static ItemWoodBase WoodMusketTrigger;
    public static ItemWoodBase WoodMusketHammer;
    public static ItemWoodBase WoodMusketBarrel;
    public static ItemWoodBase WoodMusketRamRod;

    public static ItemMold MusketShotMold;
    public static ItemMold MusketShotMoldFired;
    public static ItemMold MusketTriggerMold;
    public static ItemMold MusketTriggerMoldFired;
    public static ItemMold MusketHammerMold;
    public static ItemMold MusketHammerMoldFired;
    public static ItemMold MusketBarrelMold;
    public static ItemMold MusketBarrelMoldFired;
    public static ItemMold MusketRamRodMold;
    public static ItemMold MusketRamRodMoldFired;

    public static GaltCommonItem MusketBarrel;
    public static GaltCommonItem MusketRamRod;
    public static GaltCommonItem MusketTrigger;
    public static GaltCommonItem MusketHammer;
    public static ItemMusketStock MusketStock;


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

        Knife = new ItemKnife();

        WoodMusketShot = new ItemWoodBase(1, "ItemWoodMusketShot");
        WoodMusketTrigger = new ItemWoodBase(2, "ItemWoodMusketTrigger");
        WoodMusketHammer = new ItemWoodBase(4, "ItemWoodMusketHammer");
        WoodMusketBarrel = new ItemWoodBase(4, "ItemWoodMusketBarrel");
        WoodMusketRamRod = new ItemWoodBase(7, "ItemWoodMusketRamRod");

        MusketShotMold = new ItemMold(GaltItems.WoodMusketShot, "ItemMusketShotMold");
        MusketShotMoldFired = MusketShotMold.GetFiredMold();

        MusketTriggerMold = new ItemMold(GaltItems.WoodMusketTrigger, "ItemMusketTriggerMold");
        MusketTriggerMoldFired = MusketTriggerMold.GetFiredMold();

        MusketHammerMold = new ItemMold(GaltItems.WoodMusketHammer, "ItemMusketHammerMold");
        MusketHammerMoldFired = MusketHammerMold.GetFiredMold();

        MusketBarrelMold = new ItemMold(GaltItems.WoodMusketBarrel, "ItemMusketBarrelMold");
        MusketBarrelMoldFired = MusketBarrelMold.GetFiredMold();

        MusketRamRodMold = new ItemMold(GaltItems.WoodMusketRamRod, "ItemMusketRamRodMold");
        MusketRamRodMoldFired = MusketRamRodMold.GetFiredMold();

        MusketBarrel = new GaltCommonItem("ItemMusketBarrel");
        MusketRamRod = new GaltCommonItem("ItemMusketRamRod");
        MusketTrigger = new GaltCommonItem("ItemMusketTrigger");
        MusketHammer = new GaltCommonItem("ItemMusketHammer");
        MusketStock = new ItemMusketStock();
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
