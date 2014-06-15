package com.johngalt.gulch.entities;

import com.johngalt.gulch.GulchMod;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * Created on 6/14/2014.
 */
public class GaltEntities
{
    public static void RegisterEntities()
    {
        EntityRegistry.registerModEntity(EntityBlasterBolt.class, "BlasterBolt", EntityRegistry.findGlobalUniqueEntityId(), GulchMod.instance, 120, 3, true);
    }
}
