package com.johngalt.gulch.items;

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
public class ItemBlasterAmmo extends Item
{
    @SideOnly(Side.CLIENT)
    protected IIcon itemIcon;

    public ItemBlasterAmmo()
    {
        super();
        setCreativeTab(CreativeTabs.tabCombat);
        setUnlocalizedName(References.MODID + ".blaster_ammo");
        setMaxStackSize(64);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(References.MODID +":"+ getUnlocalizedName().substring(5));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1)
    {
        return itemIcon;
    }

}
