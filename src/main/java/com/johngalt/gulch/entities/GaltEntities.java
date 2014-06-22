package com.johngalt.gulch.entities;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.items.BulletEnum;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class GaltEntities
{
    public static void RegisterEntities()
    {
        EntityRegistry.registerModEntity(EntityBlasterBolt.class, "BlasterBolt", EntityRegistry.findGlobalUniqueEntityId(), GulchMod.instance, 120, 3, true);
    }

    public static Entity getBulletInstance(BulletEnum bulletType, World world, EntityPlayer player)
    {
        if (bulletType.equals(BulletEnum.BlasterBolt))
        {
            return new EntityBlasterBolt(world, player);
        }
        else if (bulletType.equals(BulletEnum.MusketShot))
        {
            return new EntityMusketShot(world, player);
        }

        return null;
    }
}



