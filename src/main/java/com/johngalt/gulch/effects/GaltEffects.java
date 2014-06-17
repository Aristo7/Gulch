package com.johngalt.gulch.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.world.World;

/**
 * Created on 6/15/2014.
 */
public class GaltEffects
{
    private static Minecraft mc = Minecraft.getMinecraft();
    private static World theWorld = mc.theWorld;

    public static EntityFX spawnParticle(GaltEffect par1Effect, double par2X, double par4Y, double par6Z, double par8, double par10, double par12)
    {
        if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null)
        {
            int var14 = mc.gameSettings.particleSetting;

            if (var14 == 1 && theWorld.rand.nextInt(3) == 0)
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
                        var21 = new GaltPoofFX(theWorld, par2X, par4Y, par6Z, (float) par8, (float) par10, (float) par12);
                        break;

                    case Flame:
                        var21 = new EntityFlameFX(theWorld, par2X, par4Y, par6Z, (float) par8, (float) par10, (float) par12);
                        break;
                    default:
                        return null;
                }

                mc.effectRenderer.addEffect((EntityFX) var21);
                return (EntityFX) var21;
            }
        }

        return null;
    }

    public static enum GaltEffect
    {
        Flame,
        Poof

    }
}
