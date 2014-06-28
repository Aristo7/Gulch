package com.johngalt.gulch.gui;

import com.johngalt.gulch.tileentities.GaltTileEntityMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;


/**
 * Created on 6/27/2014.
 */
public class GaltContainerMachine extends Container
{
    private GaltTileEntityMachine _MachineTileEntity;

    private int _LastBurnTime;
    private int _LastCurrentItemBurnTime;
    private int _LastCookTime;

    public GaltContainerMachine(InventoryPlayer inventory, GaltTileEntityMachine tileentity)
    {
        _MachineTileEntity = tileentity;

        this.addSlotToContainer(new Slot(tileentity, 0, 56, 35));
        this.addSlotToContainer(new Slot(tileentity, 1, 8, 62));
        this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 2, 116, 35));

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

    @Override
    public void addCraftingToCrafters(ICrafting crafting)
    {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, _MachineTileEntity.CookTime);
        crafting.sendProgressBarUpdate(this, 1, _MachineTileEntity.BurnTime);
        crafting.sendProgressBarUpdate(this, 2, _MachineTileEntity.CurrentItemBurnTime);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); i++)
        {
            ICrafting crafting = (ICrafting) this.crafters.get(i);

            if (_LastCookTime != _MachineTileEntity.CookTime)
            {
                crafting.sendProgressBarUpdate(this, 0, _MachineTileEntity.CookTime);
            }
            if (_LastBurnTime != _MachineTileEntity.BurnTime)
            {
                crafting.sendProgressBarUpdate(this, 1, _MachineTileEntity.BurnTime);
            }
            if (_LastCurrentItemBurnTime != _MachineTileEntity.CurrentItemBurnTime)
            {
                crafting.sendProgressBarUpdate(this, 2, _MachineTileEntity.CurrentItemBurnTime);
            }
        }

        _LastCookTime = _MachineTileEntity.CookTime;
        _LastBurnTime = _MachineTileEntity.BurnTime;
        _LastCurrentItemBurnTime = _MachineTileEntity.CurrentItemBurnTime;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int slot, int newValue)
    {

        _MachineTileEntity.SetStatuses(slot, newValue);
    }


    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (GaltTileEntityMachine.IsItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack) null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }
}
