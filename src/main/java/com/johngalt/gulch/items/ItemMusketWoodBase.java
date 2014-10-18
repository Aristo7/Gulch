package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by horva_000 on 10/17/2014.
 */
public class ItemMusketWoodBase extends GaltCommonItem implements IGaltRecipes
{
    public ItemMusketWoodBase()
    {
        super();
        this.setMaxStackSize(64);
    }

    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(new ShapedOreRecipe(new ItemStack(this, 1), "x ", " p", 'p', "plankWood", 'x', GaltItems.Knife.GetOreDictName()));
    }
}
