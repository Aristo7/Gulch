package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltCommonBlockContainer;
import com.johngalt.gulch.blocks.common.GaltRenderedBlockHelper;
import com.johngalt.gulch.model.GaltMoldStationModel;
import com.johngalt.gulch.tileentities.GaltMoldStationTECustRender;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 6/28/2014.
 */
public class GaltMoldStationBlock extends GaltCommonBlockContainer implements GaltRenderedBlock
{
    private GaltRenderedBlockHelper _RenderHelper;

    public GaltMoldStationBlock()
    {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setBlockBounds(0, 0, 0, 1, 0.75F, 1);

        _RenderHelper = new GaltRenderedBlockHelper(new GaltMoldStationModel(), "textures/blocks/GaltModelMoldingStation.png", this);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new GaltMoldStationTECustRender();
    }

    @Override
    public Class<? extends TileEntity> GetTileEntityCustRenderClass()
    {
        return GaltMoldStationTECustRender.class;
    }

    @Override
    public int getRenderType()
    {
        return GaltRenderedBlockHelper.getRenderType();
    }

    @Override
    public boolean isOpaqueCube()
    {
        return GaltRenderedBlockHelper.isOpaqueCube();
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return GaltRenderedBlockHelper.renderAsNormalBlock();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemBlock)
    {
        GaltRenderedBlockHelper.onBlockPlacedBy(world, x, y, z, player, itemBlock);
    }

    @Override
    public GaltRenderedBlockHelper GetRenderHelper()
    {
        return _RenderHelper;
    }
}
