package com.johngalt.gulch.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

/**
 * Created on 6/14/2014.
 */
public class GaltBlocks
{
    public static Block testBlock;
    public static Block testInteractiveBlock;
    public static Block testContainerBlock;

    public static void init()
    {
        testBlock = new GaltSimpleBlock();
        testInteractiveBlock = new GaltInteractiveBlock();
        testContainerBlock = new GaltContainerBlock();
    }

    public static void register(GaltCommonBlock block)
    {
        GameRegistry.registerBlock(block, block.getUnwrappedUnlocalizedName());
    }

    public static void register(GaltCommonContainer block)
    {
        GameRegistry.registerBlock(block, block.getUnwrappedUnlocalizedName());
    }
}
