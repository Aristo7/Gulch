package com.johngalt.gulch.items;

import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ItemMold extends GaltCommonItem implements IGaltRecipes {
    private Item _MoldBase;
    private String _MoldName;

    public ItemMold(Item moldBase, String moldName) {
        super(moldName);
        this.setMaxStackSize(1);

        _MoldBase = moldBase;
        _MoldName = moldName;
    }

    @Override
    public void RegisterRecipes() {
        GaltRecipes.RegisterRecipe(new ShapelessOreRecipe(new ItemStack(this, 1), Items.clay_ball, _MoldBase)); //GaltItems.WoodMusketShot));
    }

    public ItemMold GetFiredMold() {
        return new ItemMold(_MoldBase, _MoldName + "Fired");
    }
}
