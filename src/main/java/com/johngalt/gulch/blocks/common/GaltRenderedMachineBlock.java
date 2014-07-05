package com.johngalt.gulch.blocks.common;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.gui.GuiHandler;
import com.johngalt.gulch.proxy.ClientProxy;
import com.johngalt.gulch.tileentities.common.GaltTileEntityCustRender;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created on 7/5/2014.
 */
public abstract class GaltRenderedMachineBlock extends GaltMachineBlock
{
    private String _GulchTextureLocation;
    private ModelBase _Model;

    public GaltRenderedMachineBlock(boolean isActive, int guiID, ModelBase model, String gulchTextureLocation)
    {
        super(isActive, guiID);

        _GulchTextureLocation = gulchTextureLocation;
        _Model = model;

        this.setBlockTextureName("");

        ClientProxy.RegisterRenderedBlock(this);
        GulchMod.proxy.registerTileEntity(GetTileEntityCustRenderClass(), GetTileEntityCustRenderClass().getSimpleName());
    }

    public GaltRenderedMachineBlock(boolean isActive, ModelBase model, String gulchTextureLocation)
    {
        this(isActive, GuiHandler.GUI_ID_MACHINEBLOCK, model, gulchTextureLocation);
    }

    @Override
    public abstract TileEntity createNewTileEntity(World var1, int var2);

    @Override
    public abstract Item getItemDropped(int slot, Random random, int j);


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

    public abstract Class<? extends TileEntity> GetTileEntityCustRenderClass();

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
}
