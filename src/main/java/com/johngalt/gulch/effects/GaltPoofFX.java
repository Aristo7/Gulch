package com.johngalt.gulch.effects;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;

/**
 * Created on 6/15/2014.
 */
@SideOnly(Side.CLIENT)
public class GaltPoofFX extends EntitySmokeFX
{

    public GaltPoofFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World, par2, par4, par6, par8, par10, par12);

        this.particleMaxAge = 15;

        this.motionX = par8;
        this.motionY = par10;
        this.motionZ = par12;
    }

}
