package com.johngalt.gulch.entities;

import com.johngalt.gulch.GulchMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import static com.johngalt.gulch.entities.GaltEntities.BulletEnum.BlasterBolt;
import static com.johngalt.gulch.entities.GaltEntities.BulletEnum.MusketShot;

/**
 * Created on 6/14/2014.
 */
public class GaltEntities
{
    public static void RegisterEntities()
    {
        EntityRegistry.registerModEntity(EntityBlasterBolt.class, "BlasterBolt", EntityRegistry.findGlobalUniqueEntityId(), GulchMod.instance, 120, 3, true);
    }

    public static Entity getBulletInstance(GaltEntities.BulletEnum bulletType, World world, EntityPlayer player)
    {
        if (bulletType.equals(BlasterBolt))
        {
            return new EntityBlasterBolt(world, player);
        }
        else if (bulletType.equals(MusketShot))
        {
            // TODO: shite
        }

        return null;
    }

    public static enum BulletEnum
    {
        BlasterBolt,
        MusketShot
    }
}
