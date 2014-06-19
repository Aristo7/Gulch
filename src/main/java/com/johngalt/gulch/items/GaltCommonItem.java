package com.johngalt.gulch.items;

import com.johngalt.gulch.References;
import com.johngalt.gulch.lib.GaltLangGenerator;
import com.johngalt.gulch.lib.IGaltObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created on 6/14/2014.
 */
public class GaltCommonItem extends Item implements IGaltObject
{
    public GaltCommonItem()
    {
        super();

        this.setUnlocalizedName(GetGaltName());
        GaltLangGenerator.AddEntry(this);
        GaltItems.register(this);
    }

    public String getUnwrappedUnlocalizedName()
    {
        return super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        String tmp = String.format("item.%s%s", References.RESOURCESPREFIX, getUnwrappedUnlocalizedName());
        return tmp;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", References.RESOURCESPREFIX, getUnwrappedUnlocalizedName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + getUnwrappedUnlocalizedName());
    }

    @Override
    public String GetGaltName()
    {
        return this.getClass().getSimpleName();
    }
}
