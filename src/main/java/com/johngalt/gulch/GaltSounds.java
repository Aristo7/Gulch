package com.johngalt.gulch;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class GaltSounds
{
    public static void PlaySound(SoundsEnum sound, World world, Entity entityOrigin)
    {
        world.playSoundAtEntity(entityOrigin, References.MODID + ":" + sound.name(), 1.0F, 1.0F);
    }

    public static enum SoundsEnum
    {
        blaster_shot,
        no_ammo
    }
}

