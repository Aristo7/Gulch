package com.johngalt.gulch.items;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.References;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

/**
 * Created on 6/14/2014.
 */
public class ItemBlasterAmmo extends GaltCommonItem
{
    public ItemBlasterAmmo()
    {
        super();
        setMaxStackSize(64);
    }
}
