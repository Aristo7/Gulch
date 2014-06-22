package com.johngalt.gulch.entities;

import com.johngalt.gulch.effects.GaltEffects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created on 6/21/2014.
 */
public class EntityMusketShot extends GaltAmmoEntity
{
    // DO NOT USE
    public EntityMusketShot(World world)
    {
        super(world);
    }

    public EntityMusketShot(World world, EntityPlayer player)
    {
        super(world, player, (byte) 20, 0.01F);
    }
}
