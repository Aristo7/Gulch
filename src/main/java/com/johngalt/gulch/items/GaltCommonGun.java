package com.johngalt.gulch.items;

import com.johngalt.gulch.GaltSounds;
import com.johngalt.gulch.effects.GaltEffects;
import com.johngalt.gulch.entities.GaltEntities;
import com.johngalt.gulch.recipes.GaltRecipes;
import com.johngalt.gulch.recipes.IGaltRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 6/20/2014.
 */
public class GaltCommonGun extends GaltCommonItem implements IGaltRecipes
{
    private GaltSounds.SoundsEnum _ShotSound;
    private List<BulletEnum> _DefaultBulletTypes;
    private GaltEffects.EffectEnum _BarrelParticleEffect;
    private int _ClipSize;


    public GaltCommonGun(List<BulletEnum> bulletTypes, int clipSize, GaltSounds.SoundsEnum shotSound, GaltEffects.EffectEnum barrelParticleEffect)
    {
        super();

        _ClipSize = clipSize;
        _DefaultBulletTypes = bulletTypes;
        _ShotSound = shotSound;
        _BarrelParticleEffect = barrelParticleEffect;


        setMaxStackSize(1);
        this.setMaxDamage(_ClipSize + 1);

        //GaltRecipes.DeclareRecipes(this);

    }

    public GaltCommonGun(BulletEnum bulletTypes, int clipSize, GaltSounds.SoundsEnum shotSound, GaltEffects.EffectEnum barrelParticleEffect)
    {
        this(Arrays.asList(new BulletEnum[]{bulletTypes}), clipSize, shotSound, barrelParticleEffect);
    }

    /**
     * For shooting the gun
     *
     * @param gun    The gun being shot.
     * @param world  The world is a vampire.
     * @param player player shooting the gun
     * @return ??? Profit?
     */
    @Override
    public ItemStack onItemRightClick(ItemStack gun, World world, EntityPlayer player)
    {
        if (gun.getItemDamage() < _ClipSize)
        {
            GaltSounds.PlaySound(_ShotSound, world, player);
            if (!world.isRemote)
            {

                world.spawnEntityInWorld(GaltEntities.getBulletInstance(this, world, player));
                GaltEffects.spawnParticleAtHeldItem(_BarrelParticleEffect, player, 0.0F, 0.0F, 0.0F);

                gun.damageItem(1, player);
            }
        }
        else
        {
            GaltSounds.PlaySound(GaltSounds.SoundsEnum.no_ammo, world, player);
        }

        return gun;
    }


    /**
     * This method is where recipes for reloading get registered. Can be overwritten for more custom recipes.
     */
    @Override
    public void RegisterRecipes()
    {
        // register every combination of ammo reloading possible.
        for (BulletEnum bullet : _DefaultBulletTypes)
        {
            for (int dam = _ClipSize; dam > 0; dam--)
            {
                for (int numAmmo = dam; numAmmo > 0; numAmmo--)
                {
                    List<Object> input = new ArrayList<Object>();

                    for (int i = 0; i < numAmmo; i++)
                        input.add(new ItemStack(GaltCommonGun.GetBulletInstance(bullet), 1));

                    input.add(new ItemStack(this, 1, dam));

                    List<Object> additionalRequirements = GetAdditionalReloadRequirements(bullet, numAmmo);
                    if (additionalRequirements != null)
                    {
                        input.addAll(additionalRequirements);
                    }

                    GaltRecipes.RegisterRecipe(false, new ItemStack(this, 1, dam - numAmmo), input.toArray());
                }
            }
        }
    }

    /**
     * This is to be overwritten in derived clases if there are additional items needed for reloading the gun.
     *
     * @return The list of objects to add to the reload recipe.
     */
    protected List<Object> GetAdditionalReloadRequirements(BulletEnum bullet, int numAmmo)
    {
        return null;
    }


    /**
     * Gets the instance representing a bullet in the Enum.
     * Enums are used rather than instances initially so order of item initiation doesn't matter.
     *
     * @param bullet The enum of the type of bullet
     * @return the GaltCommonItem reference to the bullet.
     */
    public static GaltCommonItem GetBulletInstance(BulletEnum bullet)
    {
        if (bullet == BulletEnum.BlasterBolt)
        {
            return GaltItems.BlasterAmmo;
        }
        else if (bullet == BulletEnum.MusketShot)
        {
            return GaltItems.MusketShot;
        }
        else if (bullet == BulletEnum.PaperCartridge)
        {
            return GaltItems.PaperCartridge;
        }
        else if (bullet == BulletEnum.RevolverShot)
        {
            return GaltItems.RevolverShot;
        }

        return null;
    }
}
