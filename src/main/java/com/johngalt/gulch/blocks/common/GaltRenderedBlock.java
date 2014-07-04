package com.johngalt.gulch.blocks.common;

import com.johngalt.gulch.blocks.common.GaltCommonBlockContainer;
import com.johngalt.gulch.proxy.ClientProxy;
import com.johngalt.gulch.tileentities.GaltTileEntityCustRender;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created on 7/1/2014.
 */
public abstract class GaltRenderedBlock extends GaltCommonBlockContainer
{
    private String _GulchTextureLocation;
    private ModelBase _Model;

    public GaltRenderedBlock(Material material, ModelBase model, String gulchTextureLocation)
    {
        super(material);

        _GulchTextureLocation = gulchTextureLocation;
        _Model = model;

        ClientProxy.RegisterRenderedBlock(this);

//        this.setHardness(2.0F);
//        this.setResistance(5.0F);
//
//        this.setBlockTextureName("");
//
//        this.setBlockBounds(0, 0, .0625F, 1, 0.75F, 0.9375F);

    }

    public String GetTextureLocation()
    {
        return _GulchTextureLocation;
    }

    public ModelBase GetModel()
    {
        return _Model;
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
    public abstract TileEntity createNewTileEntity(World var1, int var2);
//    {
//        return new GaltTileEntityCustRender();
//    }


    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemBlock)
    {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity instanceof GaltTileEntityCustRender)
        {
            int dir = (MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3) % 4;
            ((GaltTileEntityCustRender) entity).SetDirection((short) dir);
        }


    }

    public abstract Class<? extends TileEntity> GetTileEntityCustRenderClass();
}
