package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created on 6/21/2014.
 */
public class ItemMusketShot extends GaltCommonItem implements IGaltRecipes
{
    public ItemMusketShot()
    {
        super();
        this.setMaxStackSize(64);
    }

    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(new ShapelessOreRecipe(new ItemStack(this, 16), GaltItems.IngotLead.GetOreDictName()));
    }
}
