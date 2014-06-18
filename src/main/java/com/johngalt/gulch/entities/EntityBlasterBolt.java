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
    int tickCount = -1;

    public EntityBlasterBolt(World par1World)
    {
        super(par1World);
    }

    public EntityBlasterBolt(World par1World, EntityLivingBase par2EntityLiving)
    {
        super(par1World, par2EntityLiving);

        setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0F, 1.0F);
    }

    @Override
    public void onUpdate()
    {

        super.onUpdate();

        if (tickCount >= 0)
        {
            if (tickCount++ > 1)
            {
                this.setDead();
            }
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition var1)
    {
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.0F, true);
        tickCount = 0;
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0;
    }
}
