package com.johngalt.gulch.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/21/2014.
 */
public class GaltRecipes
{
    private static List<IGaltRecipes> _RecipesObjects = new ArrayList<IGaltRecipes>();

    public static void RegisterRecipe(boolean shaped, ItemStack output, Object... inputParams)
    {
        //inner classes attach to instances of outer classes. Thats' why a new GaltRecipes is need.
        if (shaped)
        {
            GameRegistry.addShapedRecipe(output, inputParams);
        }
        else
        {
            GameRegistry.addShapelessRecipe(output, inputParams);
        }
    }

    public static void RegisterRecipes()
    {
        for (IGaltRecipes recipe : _RecipesObjects)
            recipe.RegisterRecipes();
    }

    public static void DeclareRecipes(IGaltRecipes galtRecipesObject)
    {
        _RecipesObjects.add(galtRecipesObject);
    }
}