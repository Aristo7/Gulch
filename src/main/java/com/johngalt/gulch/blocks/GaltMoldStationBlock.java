package com.johngalt.gulch.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created on 6/28/2014.
 */
public class GaltMoldStationBlock extends GaltMachineBlock
{
    private static Block _DroppedBlockItem;

    public GaltMoldStationBlock(boolean isActive)
    {
        super(isActive);

        if (!isActive)
        {
            _DroppedBlockItem = this;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return null;
    }

    @Override
    public Item getItemDropped(int slot, Random random, int j)
    {
        return Item.getItemFromBlock(_DroppedBlockItem == null ? this : _DroppedBlockItem);
    }


}
