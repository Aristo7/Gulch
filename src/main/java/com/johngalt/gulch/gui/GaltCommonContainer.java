package com.johngalt.gulch.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

/**
 * Created on 6/29/2014.
 */
public abstract class GaltCommonContainer extends Container
{

    public GaltCommonContainer(InventoryPlayer inventory, boolean standardInventorySlots/*, int startInventorySlots*/)
    {
        if (standardInventorySlots)
        {
            for (int row = 0; row < 3; row++)
            {
                for (int col = 0; col < 9; col++)
                {
                    this.addSlotToContainer(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
                }
            }

            for (int col = 0; col < 9; col++)
            {
                this.addSlotToContainer(new Slot(inventory, col, 8 + col * 18, 142));
            }
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting)
    {
        super.addCraftingToCrafters(crafting);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

    }

    @SideOnly(Side.CLIENT)
    @Override
    public abstract void updateProgressBar(int slot, int newValue);


    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }


}
