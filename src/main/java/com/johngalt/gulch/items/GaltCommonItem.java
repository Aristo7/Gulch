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

    /**
     * Registers this ore with the ore dictionary
     * @param oreDictName This is the name in the dictionary given to this ore.
     */
    protected void RegisterWithOreDictionary(String oreDictName)
    {
        OreDictionary.registerOre(oreDictName, this);
        _OreDictName = oreDictName;
    }

    /**
     * Gets the Ore Dictionary name for this item. Requires RegisterWithOreDictionary to have been ran.
     * @return The ore dictionary name.
     */
    public String GetOreDictName()
    {
        return _OreDictName;
    }

    /**
     * Gets the unlocalized name without the item.gulch prefix.
     */
    public String getUnwrappedUnlocalizedName()
    {
        return super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1);
    }

    /**
     * Gets the unlocalized name with the item.gulch: prefix
     */
    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", References.RESOURCESPREFIX, getUnwrappedUnlocalizedName());
    }

    /**
     * What the poopinickle is this for??
     * @param itemStack
     * @return
     */
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", References.RESOURCESPREFIX, getUnwrappedUnlocalizedName());
    }

    /**
     * Function override to assign the icon/texture to the item.
     * @param iconRegister used to register the icon ( iconRegister.registerIcon(name) )
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(References.RESOURCESPREFIX + getUnwrappedUnlocalizedName());
    }

    /**
     * Gets the class name to use as the unlocalized name. Generally for internal use only.
     */
    @Override
    public String GetGaltName()
    {
        return this.getClass().getSimpleName();
    }
}
