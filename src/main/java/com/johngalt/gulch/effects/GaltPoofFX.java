package com.johngalt.gulch.effects;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

/**
 * Created on 6/15/2014.
 */
@SideOnly(Side.CLIENT)
public class GaltPoofFX extends EntityFX
{

    public GaltPoofFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);


        this.particleRed = this.particleGreen = this.particleBlue = 0.4F;
        this.particleScale = 20.0F;
        this.particleMaxAge = 15;


    }

    @Override
    public void onUpdate()
    {
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
//
//        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
//        this.motionY += 0.004D;
//        this.moveEntity(this.motionX, this.motionY, this.motionZ);
//        this.motionX *= 0.8999999761581421D;
//        this.motionY *= 0.8999999761581421D;
//        this.motionZ *= 0.8999999761581421D;
//
//        if (this.onGround)
//        {
//            this.motionX *= 0.699999988079071D;
//            this.motionZ *= 0.699999988079071D;
//        }
    }
}
