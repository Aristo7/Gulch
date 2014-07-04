package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltRenderedBlock;
import com.johngalt.gulch.model.GaltModelBellow;
import com.johngalt.gulch.tileentities.GaltTileEntityCustRender;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 7/3/2014.
 */
public class GaltBellowBlock extends GaltRenderedBlock
{
    public GaltBellowBlock(Material material)
    {
        super(material, new GaltModelBellow(), "textures/blocks/GaltModelBellow.png");
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setBlockBounds(0, 0, .0625F, 1, 0.75F, 0.9375F);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new GaltTileEntityCustRender();
    }

    @Override
    public Class<? extends TileEntity> GetTileEntityCustRenderClass()
    {
        return GaltTileEntityCustRender.class;
    }
}
