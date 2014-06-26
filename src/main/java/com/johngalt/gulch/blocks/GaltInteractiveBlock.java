package com.johngalt.gulch.blocks;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.blocks.common.GaltCommonBlock;
import com.johngalt.gulch.tileentities.GaltTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class GaltInteractiveBlock extends GaltCommonBlock
{
    public GaltInteractiveBlock()
    {
        super(Material.rock);
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int meta)
    {
        return new GaltTileEntity();
    }

    /**
     * On right click, open the GUI.
     * Return true if something happens, otherwise return false
     */
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par1, float par2, float par3, float par4)
    {
        entityPlayer.openGui(GulchMod.instance, 0, world, x, y, z);
        return true;
    }
}
