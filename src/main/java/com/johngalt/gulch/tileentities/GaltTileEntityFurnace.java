package com.johngalt.gulch.tileentities;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

/**
 * Created on 6/24/2014.
 */
public class GaltTileEntityFurnace extends GaltTileEntity implements ISidedInventory
{
    private static final int[] _SlotsTop = new int[]{0};
    private static final int[] _SlotsBottom = new int[]{2, 1};
    private static final int[] _SlotsSides = new int[]{1};
    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] _FurnaceItemStacks = new ItemStack[3];
    /**
     * The number of ticks that the furnace will keep burning
     */
    public int FurnaceBurnTime;
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    public int CurrentItemBurnTime;
    /**
     * The number of ticks that the current item has been cooking for
     */
    public int FurnaceCookTime;
    private String _CustomName;

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return _FurnaceItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return _FurnaceItemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
    public ItemStack decrStackSize(int stackIndex, int removeAmount)
    {
        if (_FurnaceItemStacks[stackIndex] != null)
        {
            ItemStack itemstack;

            if (_FurnaceItemStacks[stackIndex].stackSize <= removeAmount)
            {
                itemstack = _FurnaceItemStacks[stackIndex];
                _FurnaceItemStacks[stackIndex] = null;
                return itemstack;
            }
            else
            {
                itemstack = _FurnaceItemStacks[stackIndex].splitStack(removeAmount);

                if (_FurnaceItemStacks[stackIndex].stackSize == 0)
                {
                    _FurnaceItemStacks[stackIndex] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (_FurnaceItemStacks[index] != null)
        {
            ItemStack itemstack = _FurnaceItemStacks[index];
            _FurnaceItemStacks[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack par2ItemStack)
    {
        _FurnaceItemStacks[index] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
        {
            par2ItemStack.stackSize = getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName()
    {
        return hasCustomInventoryName() ? _CustomName : "container.furnace";
    }

    /**
     * Returns if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName()
    {
        return _CustomName != null && _CustomName.length() > 0;
    }

    public void SetCustomName(String name)
    {
        _CustomName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag)
    {
        super.readFromNBT(nbtTag);
        NBTTagList nbttaglist = nbtTag.getTagList("Items", 10);
        _FurnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < _FurnaceItemStacks.length)
            {
                _FurnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        FurnaceBurnTime = nbtTag.getShort("BurnTime");
        FurnaceCookTime = nbtTag.getShort("CookTime");
        CurrentItemBurnTime = getItemBurnTime(_FurnaceItemStacks[1]);

        if (nbtTag.hasKey("CustomName", 8))
        {
            _CustomName = nbtTag.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag)
    {
        super.writeToNBT(nbtTag);
        nbtTag.setShort("BurnTime", (short) FurnaceBurnTime);
        nbtTag.setShort("CookTime", (short) FurnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < _FurnaceItemStacks.length; ++i)
        {
            if (_FurnaceItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                _FurnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbtTag.setTag("Items", nbttaglist);

        if (hasCustomInventoryName())
        {
            nbtTag.setString("CustomName", _CustomName);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int scale)
    {
        return FurnaceCookTime * scale / 200;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int scale)
    {
        if (CurrentItemBurnTime == 0)
        {
            CurrentItemBurnTime = 200;
        }

        return FurnaceBurnTime * scale / CurrentItemBurnTime;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return FurnaceBurnTime > 0;
    }

    @Override
    public void updateEntity()
    {
        boolean isBurningAtStart = FurnaceBurnTime > 0;
        boolean isBurningAtEnd = false;

        if (FurnaceBurnTime > 0)
        {
            --FurnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (FurnaceBurnTime == 0 && canSmelt())
            {
                CurrentItemBurnTime = FurnaceBurnTime = getItemBurnTime(_FurnaceItemStacks[1]);

                if (FurnaceBurnTime > 0)
                {
                    isBurningAtEnd = true;

                    if (_FurnaceItemStacks[1] != null)
                    {
                        --_FurnaceItemStacks[1].stackSize;

                        if (_FurnaceItemStacks[1].stackSize == 0)
                        {
                            _FurnaceItemStacks[1] = _FurnaceItemStacks[1].getItem().getContainerItem(_FurnaceItemStacks[1]);
                        }
                    }
                }
            }

            if (isBurning() && canSmelt())
            {
                ++FurnaceCookTime;

                if (FurnaceCookTime == 200)
                {
                    FurnaceCookTime = 0;
                    this.smeltItem();
                    isBurningAtEnd = true;
                }
            }
            else
            {
                FurnaceCookTime = 0;
            }

            if (isBurningAtStart != FurnaceBurnTime > 0)
            {
                isBurningAtEnd = true;
                BlockFurnace.updateFurnaceBlockState(FurnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (isBurningAtEnd)
        {
            this.markDirty();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (_FurnaceItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(_FurnaceItemStacks[0]);
            if (itemstack == null) return false;
            if (_FurnaceItemStacks[2] == null) return true;
            if (!_FurnaceItemStacks[2].isItemEqual(itemstack)) return false;
            int result = _FurnaceItemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= _FurnaceItemStacks[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(_FurnaceItemStacks[0]);

            if (_FurnaceItemStacks[2] == null)
            {
                _FurnaceItemStacks[2] = itemstack.copy();
            }
            else if (_FurnaceItemStacks[2].getItem() == itemstack.getItem())
            {
                _FurnaceItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --_FurnaceItemStacks[0].stackSize;

            if (_FurnaceItemStacks[0].stackSize <= 0)
            {
                _FurnaceItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack itemFuel)
    {
        if (itemFuel == null)
        {
            return 0;
        }
        else
        {
            Item item = itemFuel.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(itemFuel);
        }
    }

    public static boolean isItemFuel(ItemStack pontentialFuel)
    {
        return getItemBurnTime(pontentialFuel) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this)
        {
            return false;
        }
        else
        {
            double distance = player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D);

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

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack item)
    {
        if (slot == 2)
        {
            return false;
        }
        else if (slot == 1)
        {
            return isItemFuel(item);
        }
        else
        {
            return true;
        }
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int side)
    {
        if (side == 0)
        {
            return _SlotsBottom;
        }
        else if (side == 1)
        {
            return _SlotsTop;
        }
        else
        {
            return _SlotsSides;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
    public boolean canInsertItem(int slot, ItemStack item, int side)
    {
        return isItemValidForSlot(slot, item);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
    public boolean canExtractItem(int slot, ItemStack item, int side)
    {
        return side != 0 || slot != 1 || item.getItem() == Items.bucket;
    }
}