package com.johngalt.gulch.blocks;

import com.johngalt.gulch.blocks.common.GaltCommonBlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 7/1/2014.
 */
public class GaltBellowBlock extends GaltCommonBlockContainer
{


    public GaltBellowBlock(Material material)
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
        return new TileEntity();
    }
}
