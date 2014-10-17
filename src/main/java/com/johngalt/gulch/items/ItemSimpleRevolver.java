package com.johngalt.gulch.items;

import com.johngalt.gulch.GaltSounds;
import com.johngalt.gulch.effects.GaltEffects;
import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 6/21/2014.
 */
public class ItemSimpleRevolver extends GaltCommonGun implements IGaltRecipes
{
    public ItemSimpleRevolver()
    {
        super(Arrays.asList(new BulletEnum[]{BulletEnum.RevolverShot}), 4, GaltSounds.SoundsEnum.musket_shot, GaltEffects.EffectEnum.Flame);
    }

    /**
     * Muskets require not only the bullet, but the paper wadding and gunpowder to reload. Mussel loading for the suck.
     * Paper cartridges don't need these additional requirements.
     *
     * @return returns the object list of usually itemstacks to be added to the recipe.
     */
    @Override
    protected List<Object> GetAdditionalReloadRequirements(BulletEnum bullet, int numAmmo)
    {
        List<Object> additional = new ArrayList<Object>();
        for (int i = 0; i < numAmmo; i++)
            additional.add(new ItemStack(Items.gunpowder, 1));

        return additional;
    }

    @Override
    public void RegisterRecipes()
    {
        super.RegisterRecipes();

        GaltRecipes.RegisterRecipe(new ShapedOreRecipe(new ItemStack(this, 1),
                        " h ", "bbs", "rrt",
                        'h', GaltItems.BasicRevolverHammer,
                        'b', GaltItems.BasicRevolverBarrel,
                        's', GaltItems.BasicRevolverStock,
                        'r', GaltItems.BasicRevolverInnerRod,
                        't', GaltItems.BasicRevolverTrigger,
                        'r', GaltItems.BasicRevolverCylinder)
        );
    }
}
