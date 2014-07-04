package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltCommonBlockContainer;
import com.johngalt.gulch.tileentities.GaltTileEntityBellow;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created on 7/1/2014.
 */
public class GaltRenderedBlock extends GaltCommonBlockContainer
{


    public GaltRenderedBlock(Material material)
    {
        super(material);

        this.setHardness(2.0F);
        this.setResistance(5.0F);

        this.setBlockTextureName("");

        this.setBlockBounds(0, 0, .0625F, 1, 0.75F, 0.9375F);

    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new GaltTileEntityBellow();
    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemBlock)
    {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity instanceof GaltTileEntityBellow)
        {
            int dir = (MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3) % 4;
            ((GaltTileEntityBellow) entity).SetDirection((short)dir);
        }


    }
}
