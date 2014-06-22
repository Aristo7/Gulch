package com.johngalt.gulch.items;

import com.johngalt.gulch.GaltSounds;
import com.johngalt.gulch.effects.GaltEffects;

/**
 * Created on 6/13/2014.
 */
public class ItemBlasterRifle extends GaltCommonGun
{
    public ItemBlasterRifle()
    {
        super(BulletEnum.BlasterBolt, 5, GaltSounds.SoundsEnum.blaster_shot, GaltEffects.EffectEnum.Flame);
    }
}
