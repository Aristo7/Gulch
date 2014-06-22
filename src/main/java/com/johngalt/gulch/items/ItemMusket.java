package com.johngalt.gulch.items;

import com.johngalt.gulch.GaltSounds;
import com.johngalt.gulch.effects.GaltEffects;
import com.johngalt.gulch.entities.GaltEntities;

/**
 * Created on 6/21/2014.
 */
public class ItemMusket extends GaltCommonGun
{
    public ItemMusket()
    {
        super(BulletEnum.MusketShot, 1, GaltSounds.SoundsEnum.musket_shot, GaltEffects.EffectEnum.Flame);
    }
}
