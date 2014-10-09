package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by horva_000 on 10/8/2014.
 */
public class ItemMusketShotMold extends GaltCommonItem implements IGaltRecipes
{
    public ItemMusketShotMold()
    {
        super();
        this.setMaxStackSize(1);
    }

    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(new ShapelessOreRecipe(new ItemStack(this, 1), Items.clay_ball, GaltItems.WoodMusketShot));
    }
}
