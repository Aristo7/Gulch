package com.johngalt.gulch.items;

import com.johngalt.gulch.lib.References;
import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;


public class ItemWoodBase extends GaltCommonItem implements IGaltRecipes
{
    private int _KnifeSlot;
    private String _WoodBaseName;

    public ItemWoodBase(int knifeSlot, String woodBaseName)
    {
        super(woodBaseName);
        this.setMaxStackSize(64);

        _KnifeSlot = knifeSlot;
        _WoodBaseName = woodBaseName;
    }


    @Override
    public void RegisterRecipes()
    {
        String top = "  ";
        String middle = " p";
        String bottom = "  ";


        switch (_KnifeSlot)
        {
            case 1:
                top = "x ";
                break;
            case 2:
                top = " x";
                break;
            case 3:
                top = " x";
                middle = "p ";
                break;
            case 4:
                middle = "xp";
                break;
            case 6:
                middle = "px";
                break;
            case 7:
                bottom = "x ";
                break;
            case 8:
                bottom = " x";
                break;
            case 9:
                bottom = " x";
                middle = "p ";
                break;
            default:
                return;
        }

        GaltRecipes.RegisterRecipe(new ShapedOreRecipe(new ItemStack(this, 1), top, middle, bottom, 'p', "plankWood", 'x', GaltItems.Knife.GetOreDictName()));
    }
}
