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

    public static ItemMusketWoodBase MusketWoodBase;
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

    public static ItemBasicRevolverWoodBase BasicRevolverWoodBase;
    public static ItemWoodBase WoodRevolverShot;
    public static ItemWoodBase WoodBasicRevolverTrigger;
    public static ItemWoodBase WoodBasicRevolverHammer;
    public static ItemWoodBase WoodBasicRevolverBarrel;
    public static ItemWoodBase WoodBasicRevolverInnerRod;
    public static ItemWoodBase WoodBasicRevolverCartidge;

    public static ItemMold RevolverShotMold;
    public static ItemMold RevolverShotMoldFired;
    public static ItemMold BasicRevolverTriggerMold;
    public static ItemMold BasicRevolverTriggerMoldFired;
    public static ItemMold BasicRevolverHammerMold;
    public static ItemMold BasicRevolverHammerMoldFired;
    public static ItemMold BasicRevolverBarrelMold;
    public static ItemMold BasicRevolverBarrelMoldFired;
    public static ItemMold BasicRevolverInnerRodMold;
    public static ItemMold BasicRevolverInnerRodMoldFired;
    public static ItemMold BasicRevolverCartidgeMold;
    public static ItemMold BasicRevolverCartidgeMoldFired;

    public static GaltCommonItem BasicRevolverHammer;
    public static GaltCommonItem BasicRevolverBarrel;
    public static ItemBasicRevolverStock BasicRevolverStock;
    public static GaltCommonItem BasicRevolverInnerRod;
    public static GaltCommonItem BasicRevolverTrigger;
    public static GaltCommonItem BasicRevolverCartridge;
    public static ItemBasicRevolverCylinder BasicRevolverCylinder;


    /**
     * Initiates all the item instances.
     */
    public static void init()
    {
        BlasterRifle = new ItemBlasterRifle();
        BlasterAmmo = new ItemBlasterAmmo();
        simpleItem = new GaltSimpleItem();
        Musket = new ItemMusket();

        Wadding = new ItemWadding();
        IngotLead = new ItemIngotLead();

        GunPowder = new ItemGunPowder();

        Knife = new ItemKnife();

        buildMusketParts();
        buildRevolverParts();
    }

    private static void buildRevolverParts()
    {
        RevolverShot = new GaltCommonItem("ItemRevolverShot");
        RevolverShot.setMaxStackSize(64);

        BasicRevolverWoodBase = new ItemBasicRevolverWoodBase();

        WoodRevolverShot = new ItemWoodBase(3, "WoodRevolverShot", BasicRevolverWoodBase);
        WoodBasicRevolverTrigger = new ItemWoodBase(2, "WoodBasicRevolverTrigger", BasicRevolverWoodBase);
        WoodBasicRevolverHammer = new ItemWoodBase(4, "WoodBasicRevolverHammer", BasicRevolverWoodBase);
        WoodBasicRevolverBarrel = new ItemWoodBase(6, "WoodBasicRevolverBarrel", BasicRevolverWoodBase);
        WoodBasicRevolverInnerRod = new ItemWoodBase(7, "WoodBasicRevolverInnerRod", BasicRevolverWoodBase);
        WoodBasicRevolverCartidge = new ItemWoodBase(1, "WoodBasicRevolverCartidge", BasicRevolverWoodBase);

        RevolverShotMold = new ItemMold(GaltItems.WoodRevolverShot, "ItemBasicRevolverShotMold");
        RevolverShotMoldFired = RevolverShotMold.GetFiredMold();

        BasicRevolverTriggerMold = new ItemMold(GaltItems.WoodBasicRevolverTrigger, "ItemBasicRevolverTriggerMold");
        BasicRevolverTriggerMoldFired = BasicRevolverTriggerMold.GetFiredMold();

        BasicRevolverHammerMold = new ItemMold(GaltItems.WoodBasicRevolverHammer, "ItemBasicRevolverHammerMold");
        BasicRevolverHammerMoldFired = BasicRevolverHammerMold.GetFiredMold();

        BasicRevolverBarrelMold = new ItemMold(GaltItems.WoodBasicRevolverBarrel, "ItemBasicRevolverBarrelMold");
        BasicRevolverBarrelMoldFired = BasicRevolverBarrelMold.GetFiredMold();

        BasicRevolverInnerRodMold = new ItemMold(GaltItems.WoodBasicRevolverInnerRod, "BasicRevolverInnerRodMold");
        BasicRevolverInnerRodMoldFired = BasicRevolverInnerRodMold.GetFiredMold();

        BasicRevolverCartidgeMold = new ItemMold(GaltItems.WoodBasicRevolverCartidge, "ItemBasicRevolverCartidgeMold");
        BasicRevolverCartidgeMoldFired = BasicRevolverCartidgeMold.GetFiredMold();

        BasicRevolverHammer = new GaltCommonItem("BasicRevolverHammer");
        BasicRevolverBarrel = new GaltCommonItem("BasicRevolverBarrel");
        BasicRevolverStock = new ItemBasicRevolverStock();
        BasicRevolverInnerRod = new GaltCommonItem("BasicRevolverInnerRod");
        BasicRevolverTrigger = new GaltCommonItem("BasicRevolverTrigger");
        BasicRevolverCartridge = new GaltCommonItem("BasicRevolverCartridge");
        BasicRevolverCylinder = new ItemBasicRevolverCylinder();

        SimpleRevolver = new ItemSimpleRevolver();
    }

    private static void buildMusketParts()
    {
        MusketShot = new ItemMusketShot();
        PaperCartridge = new ItemPaperCartridge();

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
