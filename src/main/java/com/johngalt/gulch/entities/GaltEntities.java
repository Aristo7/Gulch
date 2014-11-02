package com.johngalt.gulch.entities;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.entities.mobs.ExampleMob;
import com.johngalt.gulch.items.GaltCommonGun;
import com.johngalt.gulch.items.ItemBlasterRifle;
import com.johngalt.gulch.items.ItemMusket;
import com.johngalt.gulch.items.ItemSimpleRevolver;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

/**
 * Created on 6/14/2014.
 */
public class GaltEntities
{
    public static void RegisterEntities()
    {
        registerEntity(EntityBlasterBolt.class, "BlasterBolt", 120, 3, true);
        registerMob(ExampleMob.class, "Example Mob", 64, 1, true);
    }

    @SuppressWarnings("unchecked")
    public static void registerEntity(Class<? extends Entity> entity, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(EntityBlasterBolt.class, "BlasterBolt", entityId);
        EntityRegistry.registerModEntity(EntityBlasterBolt.class, "BlasterBolt", entityId, GulchMod.instance,
                trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    public static Entity getBulletInstance(GaltCommonGun gunType, World world, EntityPlayer player)
    {
        if (gunType instanceof ItemBlasterRifle)
        {
            return new EntityBlasterBolt(world, player, gunType);
        }
        else if (gunType instanceof ItemMusket)
        {
            return new EntityMusketShot(world, player, gunType);
        }
        else if (gunType instanceof ItemSimpleRevolver)
        {
            return new EntityRevolverShot(world, player, gunType);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static void registerMob(Class<? extends Entity> mobClass, String mobName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(mobClass, mobName, entityId);
        EntityRegistry.registerModEntity(mobClass, mobName, entityId, GulchMod.instance, trackingRange, updateFrequency, sendsVelocityUpdates);

        {
            int weightedProb = 50;
            int min = 2;
            int max = 4;
            EnumCreatureType typeOfCreature = EnumCreatureType.monster;
            BiomeGenBase biome = BiomeGenBase.forest;
            EntityRegistry.addSpawn(((Class<? extends  EntityLiving>) mobClass), weightedProb, min, max, typeOfCreature, biome);
        }

        Random random = new Random(mobName.hashCode());
        int mainColor = random.nextInt() * 16777215;
        int subColor = random.nextInt() * 16777215;

        EntityList.entityEggs.put(Integer.valueOf(entityId),
                new EntityList.EntityEggInfo(entityId, mainColor, subColor));
    }
}



