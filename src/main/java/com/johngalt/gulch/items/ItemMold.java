package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ItemMold extends GaltCommonItem implements IGaltRecipes
{
    private Item _MoldBase;
    private String _MoldName;
    private boolean _Fired;

    public ItemMold(Item moldBase, String moldName)
    {
        super();
        this.setMaxStackSize(1);

        _MoldBase = moldBase;
        _MoldName = moldName;
        _Fired = false;
    }

    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(new ShapelessOreRecipe(new ItemStack(this, 1), Items.clay_ball, _MoldBase)); //GaltItems.WoodMusketShot));
    }

    @Override
    public String GetGaltName()
    {
        return _MoldName + (_Fired ? "" : "Soft");
    }

    public ItemMold GetFiredMold()
    {
        ItemMold firedMold = new ItemMold(_MoldBase, _MoldName);
        firedMold._Fired = true;
        return firedMold;
    }
}
