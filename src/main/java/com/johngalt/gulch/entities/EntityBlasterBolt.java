package com.johngalt.gulch.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class EntityBlasterBolt extends GaltAmmoEntity
{
    // DO NOT USE. INTERNAL USE ONLY
    public EntityBlasterBolt(World world)
    {
        super(world);
    }

    public EntityBlasterBolt(World world, EntityLivingBase entityLiving)
    {
        super(world, entityLiving, (byte) 10, 0.0F);
    }

    @Override
    protected void onImpact(MovingObjectPosition objectPosition)
    {
        super.onImpact(objectPosition);
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.0F, true);
    }
}
