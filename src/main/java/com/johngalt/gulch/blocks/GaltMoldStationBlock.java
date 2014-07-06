package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltRenderedBlock;
import com.johngalt.gulch.model.GaltMoldStationModel;
import com.johngalt.gulch.tileentities.GaltMoldStationTECustRender;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 6/28/2014.
 */
public class GaltMoldStationBlock extends GaltRenderedBlock
{


    public GaltMoldStationBlock()
    {
        super(Material.wood, new GaltMoldStationModel(), "textures/blocks/GaltModelMoldingStation.png");
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setBlockBounds(0, 0, 0, 1, 0.75F, 1);
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
}
