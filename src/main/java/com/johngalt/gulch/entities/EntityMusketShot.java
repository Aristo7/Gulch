package com.johngalt.gulch.entities;

import com.johngalt.gulch.items.GaltCommonGun;
import net.minecraft.entity.player.EntityPlayer;
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

    public EntityMusketShot(World world, EntityPlayer player, GaltCommonGun gun)
    {
        super(world, player, (byte)40, 0.01F, gun);
    }
}
