package com.johngalt.gulch.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 6/21/2014.
 */
public class GaltRecipes
{
    private static List<IGaltRecipes> _RecipesObjects = new ArrayList<IGaltRecipes>();

    /**
     * Registers a recipe with the game manager
     * @param recipe An IRecipe instance, including ore dictionary's Shaped/ShapelessOreRecipe
     */
    public static void RegisterRecipe(IRecipe recipe)
    {
        GameRegistry.addRecipe(recipe);
    }

    /**
     * Registers a recipe with the game manager.
     * @param shaped true if shaped, false otherwise.
     * @param output This product you get from the crafting
     * @param inputParams The needed materials to make the output.
     */
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

    /**
     * Runs the recipes method for each registered IGaltRecipes item/block
     */
    public static void RegisterRecipes()
    {
        List<String> oredict = Arrays.asList(OreDictionary.getOreNames());
        if (!oredict.contains("gunpowder"))
        {
            OreDictionary.registerOre("gunpowder", Items.gunpowder);
        }
        if (!oredict.contains("sand"))
        {
            OreDictionary.registerOre("sand", Blocks.sand);
        }
        if (!oredict.contains("charcoal"))
        {
            OreDictionary.registerOre("charcoal", new ItemStack(Items.coal, 1, 1));
        }

        for (IGaltRecipes recipe : _RecipesObjects)
            recipe.RegisterRecipes();
    }

    public static void DeclareRecipes(IGaltRecipes galtRecipesObject)
    {
        _RecipesObjects.add(galtRecipesObject);
    }
}