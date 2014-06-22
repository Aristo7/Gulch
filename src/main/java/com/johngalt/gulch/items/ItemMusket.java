package com.johngalt.gulch.items;

import com.johngalt.gulch.GaltSounds;
import com.johngalt.gulch.effects.GaltEffects;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 6/21/2014.
 */
public class ItemMusket extends GaltCommonGun
{
    public ItemMusket()
    {
        super(BulletEnum.MusketShot, 1, GaltSounds.SoundsEnum.musket_shot, GaltEffects.EffectEnum.Flame);
    }

    /**
     * Muskets require not only the bullot, but the paper wadding and gunpowder to reload. Mussel loading for the suck.
     * @return returns the object list of usually itemstacks to be added to the recipe.
     */
    @Override
    protected List<Object> GetAdditionalReloadRequirements()
    {
        List<Object> additional = new ArrayList<Object>();
        additional.add(new ItemStack(GaltItems.Wadding, 1));
        additional.add(new ItemStack(GaltItems.GunPowder, 1));
        return additional;
    }
}
