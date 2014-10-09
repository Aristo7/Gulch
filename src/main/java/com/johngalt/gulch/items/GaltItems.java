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

    public static ItemWoodBase WoodMusketShot = new ItemWoodBase(1, "ItemWoodMusketShot");
    public static ItemWoodBase WoodMusketTrigger= new ItemWoodBase(1, "ItemWoodMusketTrigger");
    public static ItemWoodBase WoodMusketHammer = new ItemWoodBase(1, "ItemWoodMusketHammer");
    public static ItemWoodBase WoodMusketBarrel = new ItemWoodBase(1, "ItemWoodMusketBarrel");

    public static ItemMold MusketShotSoftMold;
    public static ItemMold MusketShotMold;
    public static ItemMold MusketTriggerSoftMold;
    public static ItemMold MusketTriggerMold;
    public static ItemMold MusketHammerSoftMold;
    public static ItemMold MusketHammerMold;
    public static ItemMold MusketBarrelSoftMold;
    public static ItemMold MusketBarrelMold;

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

//        WoodMusketShot =
//        WoodMusketTrigger ;
//        WoodMusketHammer;
//        WoodMusketBarrel;

        MusketShotSoftMold = new ItemMold(GaltItems.WoodMusketShot, "ItemMusketShotMold");
        MusketShotMold = MusketShotSoftMold.GetFiredMold();

        MusketTriggerSoftMold = new ItemMold(GaltItems.WoodMusketTrigger, "ItemMusketTriggerMold");
        MusketTriggerMold = MusketTriggerSoftMold.GetFiredMold();

        MusketHammerSoftMold = new ItemMold(GaltItems.WoodMusketHammer, "ItemMusketHammerMold");
        MusketHammerMold = MusketHammerSoftMold.GetFiredMold();

        MusketBarrelSoftMold = new ItemMold(GaltItems.WoodMusketBarrel, "ItemMusketBarrelMold");
        MusketBarrelMold = MusketBarrelSoftMold.GetFiredMold();

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
