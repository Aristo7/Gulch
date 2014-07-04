package com.johngalt.gulch.entities.mobs;

import com.johngalt.gulch.GulchMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

import java.util.Random;

/**
 * Created by on 7/3/2014.
 */
public class EntityMobHandler
{
    public static void register()
    {
        registerMob(ExampleMob.class, "Example Mob");
    }

    public static void registerMob(Class<? extends Entity> mobClass, String mobName)
    {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(mobClass, mobName, entityId);
        EntityRegistry.registerModEntity(mobClass, mobName, entityId, GulchMod.instance, 64, 1, true);

        Random random = new Random(mobName.hashCode());
        int mainColor = random.nextInt() * 16777215;
        int subColor = random.nextInt() * 16777215;

        EntityList.entityEggs.put(Integer.valueOf(entityId),
                new EntityList.EntityEggInfo(entityId, mainColor, subColor));
    }
}
