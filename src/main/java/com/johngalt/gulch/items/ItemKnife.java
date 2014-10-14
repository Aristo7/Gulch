package com.johngalt.gulch.items;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ItemKnife extends GaltCommonItem implements IGaltRecipes
{
    public ItemKnife()
    {
        super();
        this.setMaxStackSize(1);

        this.RegisterWithOreDictionary("craftingToolKnife");
        this.setContainerItem(this);
    }


    @Override
    public void RegisterRecipes()
    {
        if (!GulchMod.DisableGulchKnife)
            GaltRecipes.RegisterRecipe(new ShapedOreRecipe(new ItemStack(this, 1), "c  ", "i  ", "s  ", 'c', "stone", 'i', "ingotIron", 's', Items.leather));
    }
}
