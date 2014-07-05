package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltRenderedBlock;
import com.johngalt.gulch.model.GaltClayFurnaceModel;
import com.johngalt.gulch.tileentities.GaltClayFurnaceTECustRender;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 7/4/2014.
 */
public class GaltClayFurnaceBlock extends GaltRenderedBlock
{
    public GaltClayFurnaceBlock()
    {
        super(Material.wood, new GaltClayFurnaceModel(), "textures/blocks/GaltModelClayFurnace.png");

        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setBlockBounds(0, 0, 0, 1, 0.9375F, 1);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new GaltClayFurnaceTECustRender();
    }

    @Override
    public Class<? extends TileEntity> GetTileEntityCustRenderClass()
    {
        return GaltClayFurnaceTECustRender.class;
    }
}
