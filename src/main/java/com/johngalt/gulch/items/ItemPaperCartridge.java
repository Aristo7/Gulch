package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created on 6/23/2014.
 */
public class ItemPaperCartridge extends GaltCommonItem implements IGaltRecipes
{
    public ItemPaperCartridge()
    {
        super();
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(new ShapelessOreRecipe(new ItemStack(GaltItems.PaperCartridge, 1), new Object[]{"dustGunpowder", GaltItems.Wadding, GaltItems.MusketShot}));
    }
}
