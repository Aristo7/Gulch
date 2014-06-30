package com.johngalt.gulch.blocks;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.blocks.common.GaltCommonBlockContainer;
import com.johngalt.gulch.gui.GuiHandler;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class GaltBlockContainerBlock extends GaltCommonBlockContainer
{
    /**
     * See TestTileBlock
     */
    public GaltBlockContainerBlock()
    {
        super(Material.rock);

    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int meta)
    {
        return new GaltTileEntityContainer();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par1, float par2, float par3, float par4)
    {
        entityPlayer.openGui(GulchMod.instance, GuiHandler.GuiTest, world, x, y, z);
        return true;
    }
}
