package com.johngalt.gulch;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class Sounds
{
    public static final String blaster_shot = "blaster_shot";

    public static void PlaySound(String soundName, World world, Entity entityOrigin)
    {
        world.playSoundAtEntity(entityOrigin, References.MODID + ":" + soundName, 1.0F, 1.0F);
    }
}
