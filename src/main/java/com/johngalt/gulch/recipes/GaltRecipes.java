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
    private static List<Recipe> _Recipes = new ArrayList<Recipe>();

    public static void RegisterRecipe(boolean shaped, ItemStack output, Object... inputParams)
    {
        //inner classes attach to instances of outer classes. Thats' why a new GaltRecipes is need.
        GaltRecipes._Recipes.add(new GaltRecipes().new Recipe(shaped, output, inputParams));
    }

    public static void RegisterRecipes()
    {
        for (Recipe recipe : _Recipes)
            recipe.Register();
    }

    public class Recipe
    {
        private ItemStack _Output;
        private Object[] _Input;
        private boolean _Shaped;

        public Recipe(boolean shaped, ItemStack output, Object[] input)
        {
            _Output = output;
            _Input = input;
            _Shaped = shaped;
        }

        public void Register()
        {
            if (_Shaped)
            {
                GameRegistry.addShapedRecipe(_Output, _Input);
            }
            else
            {
                GameRegistry.addShapelessRecipe(_Output, _Input);
            }
        }


    }
}