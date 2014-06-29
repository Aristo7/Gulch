package com.johngalt.gulch.tileentities;

import com.johngalt.gulch.items.GaltItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created on 6/29/2014.
 */
public class GaltTileEntityFurnace extends GaltTileEntityMachine
{
    public GaltTileEntityFurnace()
    {
        super();

        this.Slots.add(new MachineSlot(0, ComponentType.Input, 56, 35, new ItemStack[]{new ItemStack(Blocks.iron_ore)}));
        this.Slots.add(new MachineSlot(1, ComponentType.Fuel, 8, 62));
        this.Slots.add(new MachineSlot(2, ComponentType.Output, 116, 35));

        this.RecipeList.AddRecipe(
                new ItemStack[]{new ItemStack(Blocks.iron_ore, 2)},
                null,
                null,
                new ItemStack[]{new ItemStack(Items.iron_ingot, 3)}
        );

        this.RecipeList.AddFuel(new ItemStack(GaltItems.GunPowder, 1), 100);

        this.AllowRegisteredFuel();
    }
}
