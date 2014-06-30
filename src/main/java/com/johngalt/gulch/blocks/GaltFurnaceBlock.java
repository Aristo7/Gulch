package com.johngalt.gulch.blocks;

import com.johngalt.gulch.gui.GuiHandler;
import com.johngalt.gulch.tileentities.GaltTileEntityFurnace;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created on 6/28/2014.
 */
public class GaltFurnaceBlock extends GaltMachineBlock
{
    private static Block _DroppedBlockItem;

    public GaltFurnaceBlock(boolean isActive)
    {
        super(isActive, GuiHandler.GUI_ID_MACHINEBLOCK );

        if (!isActive)
        {
            _DroppedBlockItem = this;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2)
    {
        return new GaltTileEntityFurnace();
    }

    @Override
    public Item getItemDropped(int slot, Random random, int j)
    {
        return Item.getItemFromBlock(_DroppedBlockItem == null ? this : _DroppedBlockItem);
    }
}
