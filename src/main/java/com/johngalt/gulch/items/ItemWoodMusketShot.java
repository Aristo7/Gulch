package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by horva_000 on 10/8/2014.
 */
public class ItemWoodMusketShot extends GaltCommonItem implements IGaltRecipes
{
    public ItemWoodMusketShot()
    {
        super();
        this.setMaxStackSize(64);
    }


    @Override
    public void RegisterRecipes() {
        GaltRecipes.RegisterRecipe(new ShapedOreRecipe(new ItemStack(this, 1), "x  ", " p ", "   ", 'p', Blocks.planks, 'x', GaltItems.Knife.GetOreDictName()));
    }
}
