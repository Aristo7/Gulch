package com.johngalt.gulch.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class EntityBlasterBolt extends EntityThrowable
{

    public EntityBlasterBolt(World par1World)
    {
        super(par1World);
    }

    public EntityBlasterBolt(World par1World, EntityLivingBase par2EntityLiving)
    {
        super(par1World, par2EntityLiving);

        this.motionX *= 2;
        this.motionY *= 2;
        this.motionZ *= 2;
    }

    @Override
    protected void onImpact(MovingObjectPosition var1)
    {
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.0F, true);
        this.setDead();
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0;
    }
}
