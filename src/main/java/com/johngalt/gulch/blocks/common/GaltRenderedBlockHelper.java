package com.johngalt.gulch.blocks.common;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.blocks.GaltRenderedBlock;
import com.johngalt.gulch.proxy.ClientProxy;
import com.johngalt.gulch.tileentities.common.GaltTileEntityCustRender;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created on 7/1/2014.
 */
public class GaltRenderedBlockHelper
{
    private String _GulchTextureLocation;
    private ModelBase _Model;

    public GaltRenderedBlockHelper(ModelBase model, String gulchTextureLocation, GaltCommonBlockContainer block)
    {
        if (!(block instanceof GaltRenderedBlock))
            throw new ExceptionInInitializerError("Rendered blocks must use GaltRenderedBlock interface");

        _GulchTextureLocation = gulchTextureLocation;
        _Model = model;

        // disable default textures
        block.setBlockTextureName("");

        // register block and TE
        ClientProxy.RegisterRenderedBlock(block);

        GaltRenderedBlock renderedBlock = (GaltRenderedBlock)block;
        GulchMod.proxy.registerTileEntity(renderedBlock.GetTileEntityCustRenderClass(), renderedBlock.GetTileEntityCustRenderClass().getSimpleName());
    }

    public String GetTextureLocation()
    {
        return _GulchTextureLocation;
    }

    public ModelBase GetModel()
    {
        return _Model;
    }

    //@Override
    public static int getRenderType()
    {
        return -1;
    }

    //@Override
    public static boolean isOpaqueCube()
    {
        return false;
    }

    //@Override
    public static boolean renderAsNormalBlock()
    {
        return false;
    }

    //@Override
    //public abstract TileEntity createNewTileEntity(World var1, int var2);

    //@Override
    public static void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemBlock)
    {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity instanceof GaltTileEntityCustRender)
        {
            int dir = (MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3) % 4;
            ((GaltTileEntityCustRender) entity).SetDirection((short) dir);
        }


    }
}
