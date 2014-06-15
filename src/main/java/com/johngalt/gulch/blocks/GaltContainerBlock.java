package com.johngalt.gulch.blocks;

import com.johngalt.gulch.GulchMod;
import com.johngalt.gulch.References;
import com.johngalt.gulch.lib.Strings;
import com.johngalt.gulch.tileentities.GaltTileEntityContainer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created on 6/14/2014.
 */
public class GaltContainerBlock extends GaltCommonContainer
{
    /**
     * See TestTileBlock
     */
    protected GaltContainerBlock()
    {
        super(Material.rock);
        this.setCreativeTab(GulchMod.getCreativeTab());
        this.setBlockName(Strings.GaltContainerBlockName);

        GaltBlocks.register(this);
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2)
    {
        return new GaltTileEntityContainer();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par1, float par2, float par3, float par4)
    {
        entityPlayer.openGui(GulchMod.instance, 1, world, x ,y, z);
        return true;
    }
}
