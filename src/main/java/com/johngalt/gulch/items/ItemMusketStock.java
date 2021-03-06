package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemMusketStock extends GaltCommonItem implements IGaltRecipes
{
    public ItemMusketStock ()
    {
        super();
        this.setMaxStackSize(1);
    }

    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(new ShapedOreRecipe(new ItemStack(this, 1),
                        " k ", "spp", "   ",
                        'k', GaltItems.Knife.GetOreDictName(),
                        'p', "plankWood",
                        's', "stickWood")
        );
    }
}
