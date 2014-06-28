package com.johngalt.gulch.tileentities;

import com.johngalt.gulch.blocks.GaltBlocks;
import com.johngalt.gulch.blocks.GaltMachineBlock;
import com.johngalt.gulch.items.GaltItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created on 6/27/2014.
 */
public class GaltTileEntityMachine extends GaltTileEntity implements ISidedInventory
{
    private String _LocalizedName;

    private static final int[] _SlotsTopDestination = new int[]{0};
    private static final int[] _SlotsBottomDestination = new int[]{2, 1};
    private static final int[] _SlotsSideDestination = new int[]{1};

    private ItemStack[] _Slots = new ItemStack[3];

    private int _FurnaceSpeed = 150;
    public int BurnTime;
    public int CurrentItemBurnTime;
    public int CookTime;

    public void setGuiDisplayName(String displayName)
    {
        _LocalizedName = displayName;
    }


    public String getInventoryName()
    {
        if (hasCustomInventoryName())
        {
            return _LocalizedName;
        }
        else
        {
            return "container.Machine";
        }

    }

    public boolean hasCustomInventoryName()
    {
        return _LocalizedName == null ? false : true;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this)
        {
            return false;
        }
        else
        {
            double distance = player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D);
            return distance <= 64.0D;
        }
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack item)
    {
        switch (slot)
        {
            case 2:
                return false;
            case 1:
                return IsItemFuel(item);
            default:
                return true;
        }
    }

    public static boolean IsItemFuel(ItemStack item)
    {
        return getItemBurnTime(item) > 0;
    }

    private static int getItemBurnTime(ItemStack itemstack)
    {
        if (itemstack == null)
        {
            return 0;
        }
        else
        {
            Item item = itemstack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == GaltBlocks.portalBlock) return 15000;
            }

            if (item == GaltItems.GunPowder) return 800;
            else if (item == Items.coal) return 400;

            return GameRegistry.getFuelValue(itemstack);
        }
    }

    @Override
    public void updateEntity()
    {
        boolean burning = BurnTime > 0;
        boolean stateChanged = false;

        if (isBurning())
        {
            BurnTime--;
        }

        if (!this.worldObj.isRemote)
        {
            if (BurnTime == 0 && canSmelt())
            {
                CurrentItemBurnTime = BurnTime = getItemBurnTime(_Slots[1]);

                if (isBurning())
                {
                    stateChanged = true;

                    if (_Slots[1] != null)
                    {
                        _Slots[1].stackSize--;

                        if (_Slots[1].stackSize == 0)
                        {
                            _Slots[1] = _Slots[1].getItem().getContainerItem(_Slots[1]);
                        }
                    }
                }
            }

            if (isBurning() && canSmelt())
            {
                CookTime++;

                if (CookTime == _FurnaceSpeed)
                {
                    CookTime = 0;
                    smeltItem();

                    stateChanged = true;
                }
            }
            else
            {
                CookTime = 0;
            }

            if (burning != isBurning())
            {
                stateChanged = true;

                GaltMachineBlock.updateMachineBlockState(BurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (stateChanged)
        {
            this.markDirty();
        }
    }

    private void smeltItem()
    {
        if (canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(_Slots[0]);

            if (_Slots[2] == null)
            {
                _Slots[2] = itemstack;
            }
            else if (_Slots[2].isItemEqual(itemstack))
            {
                _Slots[2].stackSize += itemstack.stackSize;
            }

            _Slots[0].stackSize--;

            if (_Slots[0].stackSize <= 0)
            {
                _Slots[0] = null;
            }
        }
    }

    private boolean canSmelt()
    {
        if (_Slots[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(_Slots[0]);


            if (itemstack == null)
            {
                return false;
            }
            if (_Slots[2] == null)
            {
                return true;
            }
            if (!_Slots[2].isItemEqual(itemstack))
            {
                return false;
            }

            int result = _Slots[2].stackSize = itemstack.stackSize;

            return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();


        }
    }

    public boolean isBurning()
    {
        return BurnTime > 0;
    }

    public int getSizeInventory()
    {
        return _Slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return _Slots[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (_Slots[slot] != null)
        {
            ItemStack itemstack;

            if (_Slots[slot].stackSize <= amount)
            {
                itemstack = _Slots[slot];
                _Slots[slot] = null;
            }
            else
            {
                itemstack = _Slots[slot].splitStack(amount);

                if (_Slots[slot].stackSize <= 0)
                {
                    _Slots[slot] = null;
                }
            }

            return itemstack;
        }

        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (_Slots[slot] != null && _Slots[slot].stackSize > 0)
        {
            ItemStack itemstack = _Slots[slot];
            _Slots[slot] = null;
            return itemstack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack)
    {
        _Slots[slot] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        switch (side)
        {
            case 0:
                return _SlotsBottomDestination;
            case 1:
                return _SlotsTopDestination;
            default: //sides
                return _SlotsSideDestination;
        }
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack item, int side)
    {
        return isItemValidForSlot(slot, item);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack item, int side)
    {
        if (side != 0 || slot != 1 || item.getItem() == Items.bucket)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int GetBurnTimeRemainingScaled(int scale)
    {
        if (CurrentItemBurnTime == 0)
        {
            CurrentItemBurnTime = _FurnaceSpeed;
        }

        return BurnTime * scale / CurrentItemBurnTime;
    }

    public int GetCookProgressScaled(int scale)
    {
        return CookTime * scale / _FurnaceSpeed;
    }

    public void SetStatuses(int slot, int newValue)
    {
        switch (slot)
        {
            case 0:
                CookTime = newValue;
                break;
            case 1:
                BurnTime = newValue;
                break;
            case 2:
                CurrentItemBurnTime = newValue;
                break;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList list = nbt.getTagList("Items", 10);
        _Slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < list.tagCount(); i++)
        {
            NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
            byte b = compound.getByte("Slot");

            if (b >= 0 && b < _Slots.length)
            {
                _Slots[b] = ItemStack.loadItemStackFromNBT(compound);
            }
        }

        BurnTime = (int) nbt.getShort("BurnTime");
        CookTime = (int) nbt.getShort("CookTime");
        CurrentItemBurnTime = (int) nbt.getShort("CurrentItemBurnTime");

        if (nbt.hasKey("CustomName"))
        {
            _LocalizedName = nbt.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        nbt.setShort("BurnTime", (short) BurnTime);
        nbt.setShort("CookTime", (short) CookTime);
        nbt.setShort("CurrentItemBurnTime", (short) CurrentItemBurnTime);

        NBTTagList list = new NBTTagList();

        for (int i = 0; i < _Slots.length; i++)
        {
            if (_Slots[i] != null)
            {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                _Slots[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }

        nbt.setTag("Items", list);

        if (hasCustomInventoryName())
        {
            nbt.setString("CustomName", _LocalizedName);
        }
    }
}
