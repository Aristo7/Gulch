package com.johngalt.gulch.effects;

import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.world.World;

/**
 * Created on 6/17/2014.
 */
public class GaltStillFlameFX extends EntityFlameFX
{

    public GaltStillFlameFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);

        this.motionX = par8;
        this.motionY = par10;
        this.motionZ = par12;

    }
}
