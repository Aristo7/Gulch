package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemBasicRevolverCylinder extends GaltCommonItem implements IGaltRecipes
{
    public ItemBasicRevolverCylinder()
    {
        super();
    }

    @Override
    public void RegisterRecipes()
    {
        GaltRecipes.RegisterRecipe(new ShapedOreRecipe(new ItemStack(this, 1),
                        " c ", "crc", "c c",
                        'c', GaltItems.BasicRevolverCartridge,
                        'r', GaltItems.BasicRevolverInnerRod)
        );
    }
}
