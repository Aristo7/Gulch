package com.johngalt.gulch.items;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.References;
import com.johngalt.gulch.Sounds;
import com.johngalt.gulch.effects.GaltEffects;
import com.johngalt.gulch.entities.EntityBlasterBolt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import static com.johngalt.gulch.items.GaltItems.*;

/**
 * Created on 6/13/2014.
 */
public class ItemBlasterRifle extends Item
{
    @SideOnly(Side.CLIENT)
    protected IIcon itemIcon;

    public ItemBlasterRifle()
    {
        super();
        setCreativeTab(GulchMod.getCreativeTab());
        setUnlocalizedName(References.MODID + ".blaster_rifle");
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

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(References.MODID + ":" + getUnlocalizedName().substring(5));

    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1)
    {
        return itemIcon;
    }


}
