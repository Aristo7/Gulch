package com.johngalt.gulch.items;

import com.johngalt.gulch.effects.GaltEffects;
import com.johngalt.gulch.entities.EntityBlasterBolt;
import com.johngalt.gulch.lib.Sounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.johngalt.gulch.items.GaltItems.BlasterAmmo;

/**
 * Created on 6/13/2014.
 */
public class ItemBlasterRifle extends GaltCommonItem
{
    public ItemBlasterRifle()
    {
        super();
        setMaxStackSize(1);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par3EntityPlayer.inventory.consumeInventoryItem(BlasterAmmo))
        {
            Sounds.PlaySound(Sounds.blaster_shot, par2World, par3EntityPlayer);

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(new EntityBlasterBolt(par2World, par3EntityPlayer));
                GaltEffects.spawnParticleAtHeldItem(GaltEffects.GaltEffect.Flame, par3EntityPlayer, 0.0F, 0.0F, 0.0F);
            }
        }

        return par1ItemStack;
    }
}
