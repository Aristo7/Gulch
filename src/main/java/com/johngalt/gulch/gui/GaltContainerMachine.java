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

        for (int[] slot : machineslots) // machine slots start at 100
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
    public ItemStack transferStackInSlot(EntityPlayer player, int ListIndex)
    {
        ItemStack itemstack = null;
        Slot slot = getSlotFromIndex(ListIndex);


        if (slot != null && slot.getHasStack())
        {
            int slotID = slot.getSlotIndex();
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotID >= 100) //machine slots start at 100
            {
                if (!this.mergeItemStack(itemstack1, 0, 36, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else // inventory slots are < 100
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!mergeItemStackToSlotType(GaltTileEntityMachine.ComponentType.Input, itemstack1))
                    {
                        return null;
                    }
                }
                else if (_MachineTileEntity.IsItemFuel(itemstack1))
                {
                    if (!mergeItemStackToSlotType(GaltTileEntityMachine.ComponentType.Fuel, itemstack1))
                    {
                        return null;
                    }
                }
                else if (slotID >= 9 && slotID < 36)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 9, false))
                    {
                        return null;
                    }
                }
                else if (slotID >= 0 && slotID < 9 && !this.mergeItemStack(itemstack1, 9, 36, false))
                {
                    return null;
                }
                else if (!this.mergeItemStack(itemstack1, 3, 39, false))
                {
                    return null;
                }
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


    private boolean mergeItemStackToSlotType(GaltTileEntityMachine.ComponentType componentType, ItemStack itemstack)
    {
        int[] slotIDs = _MachineTileEntity.GetSlotIDsForType(componentType);

        for (int slotid : slotIDs)
        {
            if (this.mergeItemStack(itemstack, slotid, slotid, false))
                return true;
        }

        return false;
    }

    /**
     * merges provided ItemStack with the first avaliable one in the container/player inventory
     */
    @Override
    protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4)
    {
        boolean flag1 = false;
        int k = par2;

        if (par4)
        {
            k = par3 - 1;
        }

        Slot slot;
        ItemStack itemstack1;

        if (par1ItemStack.isStackable())
        {
            while (par1ItemStack.stackSize > 0 && (!par4 && k < par3 || par4 && k >= par2))
            {
                slot = getSlotFromIndex(k);
                itemstack1 = slot.getStack();

                if (itemstack1 != null && itemstack1.getItem() == par1ItemStack.getItem() && (!par1ItemStack.getHasSubtypes() || par1ItemStack.getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(par1ItemStack, itemstack1))
                {
                    int l = itemstack1.stackSize + par1ItemStack.stackSize;

                    if (l <= par1ItemStack.getMaxStackSize())
                    {
                        par1ItemStack.stackSize = 0;
                        itemstack1.stackSize = l;
                        slot.onSlotChanged();
                        flag1 = true;
                    }
                    else if (itemstack1.stackSize < par1ItemStack.getMaxStackSize())
                    {
                        par1ItemStack.stackSize -= par1ItemStack.getMaxStackSize() - itemstack1.stackSize;
                        itemstack1.stackSize = par1ItemStack.getMaxStackSize();
                        slot.onSlotChanged();
                        flag1 = true;
                    }
                }

                if (par4)
                {
                    --k;
                }
                else
                {
                    ++k;
                }
            }
        }

        if (par1ItemStack.stackSize > 0)
        {
            if (par4)
            {
                k = par3 - 1;
            }
            else
            {
                k = par2;
            }

            while (!par4 && k < par3 || par4 && k >= par2)
            {
                slot = getSlotFromIndex(k);
                itemstack1 = slot.getStack();

                if (itemstack1 == null)
                {
                    slot.putStack(par1ItemStack.copy());
                    slot.onSlotChanged();
                    par1ItemStack.stackSize = 0;
                    flag1 = true;
                    break;
                }

                if (par4)
                {
                    --k;
                }
                else
                {
                    ++k;
                }
            }
        }

        return flag1;
    }

    private Slot getSlotFromIndex(int k)
    {
        for (Object slot : this.inventorySlots)
        {
            if (slot instanceof Slot)
            {
                if (((Slot) slot).getSlotIndex() == k)
                {
                    return (Slot) slot;
                }
            }
        }

        return null;
    }
}
