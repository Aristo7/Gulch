package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltRenderedBlockHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 10/1/2014.
 */
public interface GaltRenderedBlockInterface
{
    //@Override
    public abstract int getRenderType();

    //@Override
    public abstract boolean isOpaqueCube();

    //@Override
    public abstract boolean renderAsNormalBlock();

    //@Override
    //public abstract TileEntity createNewTileEntity(World var1, int var2);

    //@Override
    public abstract void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemBlock);

    public abstract TileEntity createNewTileEntity(World var1, int var2);

    public abstract Class<? extends TileEntity> GetTileEntityCustRenderClass();

    public abstract GaltRenderedBlockHelper GetRenderHelper();
}
