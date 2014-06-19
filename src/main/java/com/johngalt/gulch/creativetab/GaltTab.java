package com.johngalt.gulch.creativetab;

import com.johngalt.gulch.items.GaltItems;
import com.johngalt.gulch.lib.GaltLangGenerator;
import com.johngalt.gulch.lib.IGaltObject;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created on 6/14/2014.
 */
public class GaltTab extends CreativeTabs implements IGaltObject
{
    public GaltTab(String name)
    {
        super(name);
        GaltLangGenerator.AddEntry(this);
    }

    @Override
    public Item getTabIconItem()
    {
        return GaltItems.simpleItem;
    }

    @Override
    public String GetGaltName()
    {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getUnlocalizedName()
    {
        return "itemGroup." + this.getTabLabel();
    }

    @Override
    public String getUnwrappedUnlocalizedName()
    {
        return this.getTabLabel();
    }
}
