package com.johngalt.gulch.creativetab;

import com.johngalt.gulch.items.GaltItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created on 6/14/2014.
 */
public class GaltTab extends CreativeTabs
{
    public GaltTab(int ID, String name)
    {
        super(ID, name);
    }

    @Override
    public Item getTabIconItem()
    {
        return GaltItems.simpleItem;
    }
}
