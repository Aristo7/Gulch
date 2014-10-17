package com.johngalt.gulch.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created on 6/21/2014.
 */
public class EntityRevolverShot extends GaltAmmoEntity
{
    // DO NOT USE
    public EntityRevolverShot(World world)
    {
        super(world);
    }

    public EntityRevolverShot(World world, EntityPlayer player)
    {
        super(world, player, (byte) 14, 0.01F);
    }
}
