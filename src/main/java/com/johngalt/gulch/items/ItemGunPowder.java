package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created on 6/23/2014.
 */
public class ItemGunPowder extends GaltCommonItem implements IGaltRecipes
{
    public ItemGunPowder()
    {
        super();

        this.RegisterWithOreDictionary("gunpowder");
    }

    /**
     * There are three ways to make gun powder through this mod: sand, lava, coal/coaldust/charcoal.
     */
    @Override
    public void RegisterRecipes()
    {
        ShapelessOreRecipe recipe = new ShapelessOreRecipe(new ItemStack(this, 16), new Object[]{"sand", Items.coal, Items.lava_bucket});
        GaltRecipes.RegisterRecipe(recipe);

        ShapelessOreRecipe recipe2 = new ShapelessOreRecipe(new ItemStack(this, 16), new Object[]{"sand", "dustCoal", Items.lava_bucket});
        GaltRecipes.RegisterRecipe(recipe2);

        ShapelessOreRecipe recipe3 = new ShapelessOreRecipe(new ItemStack(this, 16), new Object[]{"sand", "charcoal", Items.lava_bucket});
        GaltRecipes.RegisterRecipe(recipe3);
    }
}
