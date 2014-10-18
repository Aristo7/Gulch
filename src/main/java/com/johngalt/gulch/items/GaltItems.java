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

    public static GaltCommonItem RevolverShot;
    public static ItemSimpleRevolver SimpleRevolver;

    public static GaltCommonItem BasicRevolverHammer;
    public static GaltCommonItem BasicRevolverBarrel;
    public static ItemBasicRevolverStock BasicRevolverStock;
    public static GaltCommonItem BasicRevolverInnerRod;
    public static GaltCommonItem BasicRevolverTrigger;
    public static GaltCommonItem BasicRevolverCylinder;
    public static ItemMusketWoodBase MusketWoodBase;
    public static ItemBasicRevolverWoodBase BasicRevolverWoodBase;
    public static ItemWoodBase WoodBasicRevolverTrigger;
    public static ItemWoodBase WoodBasicRevolverHammer;
    public static ItemWoodBase WoodBasicRevolverBarrel;
    public static ItemWoodBase WoodBasicRevolverInnerRod;
    public static ItemWoodBase WoodBasicRevolverCartidge;


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

        MusketWoodBase = new ItemMusketWoodBase();

        WoodMusketShot = new ItemWoodBase(1, "ItemWoodMusketShot", MusketWoodBase);
        WoodMusketTrigger = new ItemWoodBase(2, "ItemWoodMusketTrigger", MusketWoodBase);
        WoodMusketHammer = new ItemWoodBase(4, "ItemWoodMusketHammer", MusketWoodBase);
        WoodMusketBarrel = new ItemWoodBase(6, "ItemWoodMusketBarrel", MusketWoodBase);
        WoodMusketRamRod = new ItemWoodBase(7, "ItemWoodMusketRamRod", MusketWoodBase);

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

        RevolverShot = new GaltCommonItem();
        RevolverShot.setMaxStackSize(64);

        BasicRevolverWoodBase = new ItemBasicRevolverWoodBase();


        WoodBasicRevolverTrigger = new ItemWoodBase(2, "ItemWoodMusketTrigger", BasicRevolverWoodBase);
        WoodBasicRevolverHammer = new ItemWoodBase(4, "WoodBasicRevolverHammer", BasicRevolverWoodBase);
        WoodBasicRevolverBarrel = new ItemWoodBase(6, "WoodBasicRevolverBarrel", BasicRevolverWoodBase);
        WoodBasicRevolverInnerRod = new ItemWoodBase(7, "WoodBasicRevolverInnerRod", BasicRevolverWoodBase);
        WoodBasicRevolverCartidge = new ItemWoodBase(1, "WoodBasicRevolverCartidge", BasicRevolverWoodBase);


        BasicRevolverHammer = new GaltCommonItem("BasicRevolverHammer");
        BasicRevolverBarrel = new GaltCommonItem("BasicRevolverBarrel");
        BasicRevolverStock = new ItemBasicRevolverStock();
        BasicRevolverInnerRod = new GaltCommonItem("BasicRevolverInnerRod");
        BasicRevolverTrigger = new GaltCommonItem("BasicRevolverTrigger");
        BasicRevolverCylinder = new GaltCommonItem("BasicRevolverCylinder");

        SimpleRevolver = new ItemSimpleRevolver();
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
