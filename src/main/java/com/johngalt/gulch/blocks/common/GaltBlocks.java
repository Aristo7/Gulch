package com.johngalt.gulch.blocks.common;

import com.johngalt.gulch.blocks.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created on 6/14/2014.
 */
public class GaltBlocks
{
    public static Block testBlock;
    public static Block testInteractiveBlock;
    public static Block testContainerBlock;

    public static Block portalBlock;
    public static Block portalFrame;

    public static Block roadBlock;

    public static GaltFurnaceBlock GaltFurnaceIdle;
    public static GaltFurnaceBlock GaltFurnaceActive;

    public static GaltBellowBlock BellowBlock;
    public static GaltClayFurnaceBlock ClayFurnaceBlock;
    public static GaltMoldStationBlock MoldStationBlock;

    public static Block water;

    public static void init()
    {
        testBlock = new GaltSimpleBlock();
        testInteractiveBlock = new GaltInteractiveBlock();
        testContainerBlock = new GaltBlockContainerBlock();

        portalBlock = new GulchPortalBlock();
        portalFrame = new GulchPortalFrameBlock();

        roadBlock = new GaltRoadBlock();

        GaltFurnaceActive = new GaltFurnaceBlock(true);
        GaltFurnaceIdle = new GaltFurnaceBlock(false);

        BellowBlock = new GaltBellowBlock();
        ClayFurnaceBlock = new GaltClayFurnaceBlock();
        MoldStationBlock = new GaltMoldStationBlock();


        water = new GaltWaterMultiBlockStorageBlock();

    }

    public static void register(GaltCommonBlock block)
    {
        GameRegistry.registerBlock(block, block.getUnwrappedUnlocalizedName());
    }

    public static void register(GaltCommonBlockContainer block)
    {
        GameRegistry.registerBlock(block, block.getUnwrappedUnlocalizedName());
    }
}
