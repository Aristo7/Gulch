package com.johngalt.gulch.effects;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.network.ParticleMessage;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.EntityLivingBase;

/**
 * Created on 6/15/2014.
 */

public class GaltEffects
{

//    private static World theWorld = mc.theWorld;

    public static EntityFX spawnParticleAtHeldItem(EffectEnum par1Effect, EntityLivingBase par3EntityPlayer, double par8, double par10, double par12)
    {
        float zOffset = (float) Math.cos(Math.toRadians(par3EntityPlayer.rotationYaw + 40)) * 0.25F;
        float xOffset = (float) -(Math.sin(Math.toRadians(par3EntityPlayer.rotationYaw + 40))) * 0.25F;
        float yOffset = (float) -(Math.sin(Math.toRadians(par3EntityPlayer.rotationPitch))) * 0.25F;

        return GaltEffects.spawnParticle(par1Effect, par3EntityPlayer.posX + xOffset, par3EntityPlayer.posY + 1.65F + yOffset, par3EntityPlayer.posZ + zOffset, par8, par10, par12, par3EntityPlayer.dimension);
    }

    public static EntityFX spawnParticle(EffectEnum par1Effect, double par2X, double par4Y, double par6Z, double par8, double par10, double par12, int dimension)
    {
        GulchMod.GaltNetwork.sendToAllAround(new ParticleMessage(par1Effect, par2X, par4Y, par6Z, par8, par10, par12), new NetworkRegistry.TargetPoint(dimension, par2X, par4Y, par6Z, 40));
        //return AddEffectToWorld(par1Effect, par2X, par4Y, par6Z, par8, par10, par12);
        return null;
    }

    @SideOnly(Side.CLIENT)
    public static EntityFX AddEffectToWorld(EffectEnum par1Effect, double par2X, double par4Y, double par6Z, double par8, double par10, double par12)
    {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null)
        {
            int var14 = mc.gameSettings.particleSetting;

            if (var14 == 1 && mc.theWorld.rand.nextInt(3) == 0)
            {
                var14 = 2;
            }

            double var15 = mc.renderViewEntity.posX - par2X;
            double var17 = mc.renderViewEntity.posY - par4Y;
            double var19 = mc.renderViewEntity.posZ - par6Z;
            EntityFX var21 = null;
            double var22 = 16.0D;

            if (var15 * var15 + var17 * var17 + var19 * var19 > var22 * var22)
            {
                return null;
            }
            else if (var14 > 1)
            {
                return null;
            }
            else
            {
                switch (par1Effect)
                {
                    case Poof:
                        var21 = new GaltPoofFX(mc.theWorld, par2X, par4Y, par6Z, (float) par8, (float) par10, (float) par12);
                        break;

                    case Flame:
                        var21 = new GaltStillFlameFX(mc.theWorld, par2X, par4Y, par6Z, (float) par8, (float) par10, (float) par12);
                        break;
                    default:
                        return null;
                }

                mc.effectRenderer.addEffect(var21);
                return var21;
            }
        }
        return null;
    }

    public static enum EffectEnum
    {
        Flame,
        _ShotPartical,
        Poof

    }
}
