package com.johngalt.gulch.items;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.lib.GaltLangGenerator;
import com.johngalt.gulch.lib.IGaltObject;
import com.johngalt.gulch.lib.References;
import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created on 6/14/2014.
 */
public class GaltCommonItem extends Item implements IGaltObject
{
    private String _OreDictName;

    public GaltCommonItem()
    {
        super();

        this.setUnlocalizedName(GetGaltName());
        GaltLangGenerator.AddEntry(this);
        GaltItems.register(this);
        this.setCreativeTab(GulchMod.getCreativeTab());

        if (this instanceof IGaltRecipes) GaltRecipes.DeclareRecipes((IGaltRecipes) this);
    }

    protected void RegisterWithOreDictionary(String oreDictName)
    {
        OreDictionary.registerOre(oreDictName, this);
        _OreDictName = oreDictName;
    }

    public String GetOreDictName()
    {
        return _OreDictName;
    }

    public String getUnwrappedUnlocalizedName()
    {
        return super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", References.RESOURCESPREFIX, getUnwrappedUnlocalizedName());
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
