package com.johngalt.gulch.entities;

import com.johngalt.gulch.effects.GaltEffects;
import com.johngalt.gulch.items.GaltCommonGun;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
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
    private int _TickDelay;
    private GaltEffects.EffectEnum _ShotPartical;
    private boolean _Fired;

    public GaltAmmoEntity(World par1World)
    {
        super(par1World);
    }

    public GaltAmmoEntity(World world, EntityLivingBase entityLiving, byte damageOnHit, float gravityAffect, GaltCommonGun gun)
    {
        this(world, entityLiving, damageOnHit, gravityAffect, gun, 0);
    }

    public GaltAmmoEntity(World world, EntityLivingBase entityLiving, byte damageOnHit, float gravityAffect, GaltCommonGun gun, int tickDelay)
    {
        super(world, entityLiving);

        _Fired = false;
        _TickDelay = tickDelay;
        _DamageOnHit = damageOnHit;
        _GravityAffect = gravityAffect;
        _ShotPartical = gun.GetBarrelPartical();
//        setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0F, 1.0F);
    }

    @Override
    public void onUpdate()
    {
        if (ticksExisted >= _TickDelay)
        {

            if (!_Fired)
            {
                EntityLivingBase thrower = this.getThrower();
                this.setLocationAndAngles(thrower.posX, thrower.posY + (double)thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
                this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
                this.posY -= 0.10000000149011612D;
                this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
                this.setPosition(this.posX, this.posY, this.posZ);
                this.yOffset = 0.0F;

                float f = 0.4F;
                this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
                this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
                this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * f);
                this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 3.0F, 1.0F);

                if (!this.worldObj.isRemote)
                {
                    GaltEffects.spawnParticleAtHeldItem(_ShotPartical, this.getThrower(), 0.0F, 0.0F, 0.0F);
                }

                _Fired = true;
            }

            super.onUpdate();


            if (this.ticksExisted > 100) this.setDead();

            if (tickCount >= 0)
            {
                if (++tickCount > 1)
                {
                    this.setDead();
                }
            }
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObject)
    {
        GaltEffects.spawnParticle(GaltEffects.EffectEnum.Poof, this.posX, this.posY, this.posZ, 0.0F, 0.0F, 0.0F);

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
