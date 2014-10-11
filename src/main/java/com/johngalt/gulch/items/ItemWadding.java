package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created on 6/22/2014.
 */
public class ItemWadding extends GaltCommonItem implements IGaltRecipes
{
    public ItemWadding()
    {
        super();
    }

    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(false, new ItemStack(this, 8), new ItemStack(Items.paper, 1), new ItemStack(GaltItems.Knife));
    }
}
