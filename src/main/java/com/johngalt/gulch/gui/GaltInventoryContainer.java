package com.johngalt.gulch.gui;

import com.johngalt.gulch.inventory.GaltSlotTest;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created on 6/14/2014.
 */
public class GaltInventoryContainer extends Container
{
    private GaltTileEntityContainer tile;

    public GaltInventoryContainer(InventoryPlayer inventory, GaltTileEntityContainer tileEntityTestContainer)
    {
        tile = tileEntityTestContainer;
        bindPlayerInventory(inventory);
    }

    /*
    Add slots to our GUI.
    The id's are for the slot numbers.
    For the rest, the i * 18 and j * 18 is always the same.
    The other numbers can change, depending on your gui.
     */
    private void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        int id = 0; //ID's for player inventory

        for(int i = 0; i < 9; i++)
        {
            //Adds player hotbar
            addSlotToContainer(new Slot(inventoryPlayer, id, i * 18 + 8, 189));
            id++;
        }
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                //Adds player inventory
                addSlotToContainer(new Slot(inventoryPlayer, id ,j * 18 + 8, i * 18 + 131 ));
                id++;
            }
        }

        int id2 = 0;
        for(int i = 0; i < 3; i ++)
        {
            for(int j = 0; j < 2; j++)
            {
                addSlotToContainer(new GaltSlotTest(tile, id2, i * 18 + 62, j * 18 + 21)); //Adds custom slots
                id2++;
            }
        }
        addSlotToContainer(new GaltSlotTest(tile, id2, 81, 95)); //Adds custom output
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int slotIndex)
    {
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }
}
