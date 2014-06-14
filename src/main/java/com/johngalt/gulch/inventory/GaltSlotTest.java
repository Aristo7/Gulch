package com.johngalt.gulch.inventory;

import com.johngalt.gulch.items.GaltItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created on 6/14/2014.
 */
public class GaltSlotTest extends Slot
{
    public GaltSlotTest(IInventory inventory, int slotIndex, int x, int y)
    {
        super(inventory, slotIndex, x, y);
    }

    /*
    In this case, you can only place this item
     */
    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        return par1ItemStack.getItem() == GaltItems.simpleItem;
    }
}
