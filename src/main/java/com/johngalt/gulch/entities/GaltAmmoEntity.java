package com.johngalt.gulch.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created on 6/21/2014.
 */
public class GaltAmmoEntity extends EntityThrowable
{
    int tickCount = -1;
    public byte _DamageOnHit;
    public float _GravityAffect;

    public GaltAmmoEntity(World par1World)
    {
        super(par1World);
    }

    public GaltAmmoEntity(World world, EntityLivingBase entityLiving, byte damageOnHit, float gravityAffect)
    {
        super(world, entityLiving);

        _DamageOnHit = damageOnHit;
        _GravityAffect = gravityAffect;
        setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0F, 1.0F);
    }

    @Override
    public void onUpdate()
    {

        super.onUpdate();

        if (tickCount >= 0)
        {
            if (++tickCount > 1)
            {
                this.setDead();
            }
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObject)
    {
        if (movingObject.entityHit != null)
        {
            movingObject.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float) _DamageOnHit);
        }
        tickCount = 0;
    }

    @Override
    protected float getGravityVelocity()
    {
        return _GravityAffect;
    }
}
