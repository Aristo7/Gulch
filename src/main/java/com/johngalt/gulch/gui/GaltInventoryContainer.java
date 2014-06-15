package com.johngalt.gulch.gui;

import com.johngalt.gulch.inventory.GaltSlotTest;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created on 6/14/2014.
 */
public class GaltInventoryContainer extends Container
{
    private GaltTileEntityContainer tile;
    private int smashTimeRemaining;
    private int smashTime;

    public GaltInventoryContainer(InventoryPlayer inventory, GaltTileEntityContainer tileEntityTestContainer)
    {
        tile = tileEntityTestContainer;
        bindPlayerInventory(inventory);
    }

    /*
    To update our progress bar, we need to send a packet.
    In MC, there is an easy way to send an update for a progress bar
    We need to 'register' what values we want to send
     */
    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tile.getSmashTime());
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tile.getSmashTimeRemaining());
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (Object i : this.crafters)
        {
            ICrafting icrafting = (ICrafting) i;

            if (this.smashTime != this.tile.getSmashTime())
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tile.getSmashTime());
                this.smashTime = this.tile.getSmashTime();
            }
            if (this.smashTimeRemaining != this.tile.getSmashTimeRemaining())
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tile.getSmashTimeRemaining());
                this.smashTimeRemaining = this.tile.getSmashTimeRemaining();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.tile.smashTime = par2;
        }

        if (par1 == 1)
        {
            this.tile.smashTimeRemaining = par2;
        }
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

        for (int i = 0; i < 9; i++)
        {
            //Adds player hotbar
            addSlotToContainer(new Slot(inventoryPlayer, id, i * 18 + 8, 189));
            id++;
        }
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                //Adds player inventory
                addSlotToContainer(new Slot(inventoryPlayer, id, j * 18 + 8, i * 18 + 131));
                id++;
            }
        }

        int id2 = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 2; j++)
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
