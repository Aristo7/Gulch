package com.johngalt.gulch.items;

import com.johngalt.gulch.GaltSounds;
import com.johngalt.gulch.effects.GaltEffects;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 6/21/2014.
 */
public class ItemMusket extends GaltCommonGun
{
    public ItemMusket()
    {
        super(Arrays.asList(new BulletEnum[]{BulletEnum.MusketShot, BulletEnum.PaperCartridge}), 1, GaltSounds.SoundsEnum.musket_shot, GaltEffects.EffectEnum.Flame);
    }

    /**
     * Muskets require not only the bullet, but the paper wadding and gunpowder to reload. Mussel loading for the suck.
     * Paper cartridges don't need these additional requirements.
     * @return returns the object list of usually itemstacks to be added to the recipe.
     */
    @Override
    protected List<Object> GetAdditionalReloadRequirements(BulletEnum bullet)
    {
        if (bullet.equals(BulletEnum.MusketShot))
        {
            List<Object> additional = new ArrayList<Object>();
            additional.add(new ItemStack(GaltItems.Wadding, 1));
            additional.add(new ItemStack(Items.gunpowder, 1));
            return additional;
        }
        else
        {
            return null;
        }
    }


}
