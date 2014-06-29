package com.johngalt.gulch.gui;

import com.johngalt.gulch.tileentities.GaltTileEntityMachine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.List;


/**
 * Created on 6/27/2014.
 */
public class GaltContainerMachine extends GaltCommonContainer
{
    private GaltTileEntityMachine _MachineTileEntity;

    private int _LastBurnTime;
    private int _LastCurrentItemBurnTime;
    private int _LastCookTime;

    public GaltContainerMachine(InventoryPlayer inventory, GaltTileEntityMachine tileentity)
    {
        super(inventory, true);
        _MachineTileEntity = tileentity;

        List<int[]> machineslots = tileentity.GetSlotsForContainer();

        for (int[] slot : machineslots)
        {
            if (slot[3] == 0)
            {
                this.addSlotToContainer(new Slot(tileentity, slot[0], slot[1], slot[2]));
            }
            else
            {
                this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, slot[0], slot[1], slot[2]));
            }
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

        for (Object crafter : this.crafters)
        {
            ICrafting crafting = (ICrafting) crafter;

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
    public void updateProgressBar(int status, int newValue)
    {

        _MachineTileEntity.SetStatuses(status, newValue);
    }


    @Override
    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(slotID);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotID < _MachineTileEntity.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotID > _MachineTileEntity.getSizeInventory())
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (_MachineTileEntity.IsItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (slotID >= 3 && slotID < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (slotID >= 30 && slotID < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
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
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
